package tech.qijin.cell.task.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.db.model.Drops;
import tech.qijin.cell.account.service.CellDropsService;
import tech.qijin.cell.account.service.bo.DropsBo;
import tech.qijin.cell.counting.service.CellCountingService;
import tech.qijin.cell.task.base.CacheKey;
import tech.qijin.cell.task.base.TaskKind;
import tech.qijin.cell.task.base.TaskRecordStatus;
import tech.qijin.cell.task.db.model.Task;
import tech.qijin.cell.task.db.model.TaskRecord;
import tech.qijin.cell.task.helper.CellTaskHelper;
import tech.qijin.cell.task.server.vo.TaskReqVo;
import tech.qijin.cell.task.service.CellTaskService;
import tech.qijin.cell.task.service.bo.TaskBo;
import tech.qijin.util4j.aop.util.CasAssert;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.redis.RedisUtil;
import tech.qijin.util4j.utils.AsyncUtil;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellTaskServiceImpl implements CellTaskService {
    @Autowired
    private CellTaskService cellTaskService;
    @Autowired
    private CellTaskHelper cellTaskHelper;
    @Autowired
    private CellCountingService cellCountingService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CellDropsService cellDropsService;

    @Override
    public void triggerTask(Long userId) {
        if (isTaskTriggered(userId)) return;
        // 所有的Task
        List<Task> tasks = cellTaskHelper.listAllTask();
        if (CollectionUtils.isEmpty(tasks)) return;
        // 用户已经存在的Task
        List<TaskRecord> records = cellTaskHelper.listTaskRecord(userId);
        Set<Long> taskIds = records.stream().map(TaskRecord::getTaskId).collect(Collectors.toSet());
        tasks.stream().forEach(task -> {
            // 找用户已经存在的Task和全量Task的差值
            if (!taskIds.contains(task.getId())) {
                triggerOneTask(userId, task);
            }
        });
        cacheTaskTriggered(userId);
    }

    @Override
    public List<TaskBo> listUserTask(Long userId) {
        List<TaskRecord> records = cellTaskHelper.listTaskRecord(userId);
        if (CollectionUtils.isEmpty(records)) return Collections.emptyList();
        Map<Long, Task> taskMap = cellTaskHelper.mapAllTask();
        records.sort((record1, record2) -> {
            Task task1 = taskMap.get(record1.getTaskId());
            Task task2 = taskMap.get(record2.getTaskId());
            Integer sort1 = task1 != null ? task1.getOrder() : Integer.MAX_VALUE;
            Integer sort2 = task2 != null ? task2.getOrder() : Integer.MAX_VALUE;
            return sort1.compareTo(sort2);
        });
        List<TaskBo> taskBos = records.stream().map(record -> TaskBo.builder()
                .task(taskMap.get(record.getTaskId()))
                .taskRecord(record)
                .curr(cellCountingService.query(userId, record.getCountingCode()))
                .build()).collect(Collectors.toList());
        taskStatusMakeUp(taskBos);
        return taskBos;
    }

    @Override
    public TaskBo getClaimableTask(Long userId) {
        List<TaskBo> taskBos = listUserTask(userId);
        if (CollectionUtils.isEmpty(taskBos)) return null;
        List<TaskBo> claimableTaskBos = listClaimableTask(taskBos);
        if (CollectionUtils.isNotEmpty(claimableTaskBos)) return claimableTaskBos.get(0);
        return listDailyTask(taskBos).stream().findFirst().orElse(null);
    }

    @Override
    @Retryable(value = {Exception.class}, backoff = @Backoff(delay = 10L, multiplier = 2.0))
    public void onFinishTask(Long userId, String countingCode) {
        List<Task> tasks = cellTaskHelper.listTasksByCountingCode(countingCode);
        if (CollectionUtils.isEmpty(tasks)) {
            log.error("CellTaskService no relative task with countingCode={}", countingCode);
            return;
        }
        cellTaskService.onFinishTasks(userId, tasks);
    }

    @Override
    @Retryable(value = {Exception.class}, backoff = @Backoff(delay = 10L, multiplier = 2.0))
    public void onFinishTask(Long userId, Task task) {
        TaskRecord taskRecord = cellTaskHelper.getTaskRecord(userId, task.getId());
        if (taskRecord == null) {
            log.error("CellTaskService TaskRecord not exists, userId={}, taskId={}", userId, task.getId());
            return;
        }
        if (!TaskRecordStatus.PROCESSING.equals(taskRecord.getStatus())) {
            log.warn("CellTaskService TaskRecord has already finished, userId={}, taskRecordId={}", taskRecord.getId());
            return;
        }

        log.warn("CellTaskService TaskRecord finished, userId={}, taskRecordId={}", taskRecord.getId());
        CasAssert.isTrue(cellTaskHelper.updateTaskStatus(taskRecord, TaskRecordStatus.FINISH_UNCLAIMED));
    }

    @Override
    public void onFinishTasks(Long userId, List<Task> tasks) {
        tasks.stream().parallel().forEach(task -> cellTaskService.onFinishTask(userId, task));
    }

    @Override
    public DropsBo claimTaskReward(Long userId, Long taskRecordId) {
        TaskRecord record = cellTaskHelper.getTaskRecord(taskRecordId);
        MAssert.notNull(record, ResEnum.INVALID_PARAM);
        MAssert.isTrue(record.getUserId().equals(UserUtil.getUserId()), ResEnum.FORBIDDEN);
        DropsBo dropsBo = doClaimReward(userId, record);
        if (dropsBo != null) {
            cellTaskHelper.updateTaskStatus(record, TaskRecordStatus.FINISH_CLAIMED);
        }
        return dropsBo;
    }

    public void triggerOneTask(Long userId, Task task) {
        TaskRecord record = new TaskRecord();
        record.setUserId(userId);
        record.setTaskId(task.getId());
        record.setCountingCode(task.getCountingCode());
        record.setTarget(task.getTarget());
        record.setTaskFormat(cellTaskHelper.getTaskFormat(userId, task));
        Date now = DateUtil.now();
        Date startTime = now;
        Date endTime = DateUtil.forever();
        switch (task.getKind()) {
            case ONCE:
                break;
            case DAILY:
                startTime = DateUtil.getDayBegin(now);
                endTime = DateUtil.getDayEnd(now);
                break;
            case WEEKLY:
                startTime = DateUtil.firstDayOfWeek(now, true);
                endTime = DateUtil.lastDayOfWeek(now, true);
                break;
            case MONTHLY:
                startTime = DateUtil.firstDayOfMonth(now, true);
                endTime = DateUtil.lastDayOfMonth(now, true);
                break;
            case YEARLY:
                startTime = DateUtil.firstDayOfYear(now, true);
                endTime = DateUtil.lastDayOfYear(now, true);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        record.setStartTime(startTime);
        record.setEndTime(endTime);
        record.setStatus(TaskRecordStatus.PROCESSING);
        if (task.getClaimableWhenCreate()) {
            record.setStatus(TaskRecordStatus.FINISH_UNCLAIMED);
        }
        cellTaskHelper.insertTaskRecord(record);
    }

    /**
     * 列出所有的可领取奖励任务
     *
     * @param taskBos
     * @return
     */
    private List<TaskBo> listClaimableTask(List<TaskBo> taskBos) {
        return taskBos.stream()
                .filter(taskBo -> TaskRecordStatus.FINISH_UNCLAIMED.equals(taskBo.getTaskRecord().getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * 列出所有的永久任务
     *
     * @param taskBos
     * @return
     */
    private List<TaskBo> listOnceTask(List<TaskBo> taskBos) {
        return taskBos.stream()
                .filter(taskBo -> TaskKind.ONCE.equals(taskBo.getTask().getKind()))
                .collect(Collectors.toList());
    }

    /**
     * 列出所有的每日任务
     *
     * @param taskBos
     * @return
     */
    private List<TaskBo> listDailyTask(List<TaskBo> taskBos) {
        return taskBos.stream()
                .filter(taskBo -> TaskKind.DAILY.equals(taskBo.getTask().getKind()))
                .collect(Collectors.toList());
    }

    /**
     * 任务状态补偿
     * 将PROCESSING的状态修改为FINISH_UNCLAIMED
     *
     * @param taskBos
     */
    public void taskStatusMakeUp(List<TaskBo> taskBos) {
        taskBos.stream()
                .filter(taskBo -> TaskRecordStatus.PROCESSING.equals(taskBo.getTaskRecord().getStatus()))
                .filter(taskBo -> taskBo.getCurr().equals(taskBo.getTask().getTarget()))
                .parallel()
                .forEach(taskBo -> {
                    cellTaskHelper.updateTaskStatus(taskBo.getTaskRecord(), TaskRecordStatus.FINISH_UNCLAIMED);
//                    AsyncUtil.submit(() -> cellTaskHelper.updateTaskStatus(taskBo.getTaskRecord(), TaskRecordStatus.FINISH_UNCLAIMED));
                });
    }

    private DropsBo doClaimReward(Long userId, TaskRecord record) {
        switch (record.getRewardType()) {
            case DROPS:
                return cellDropsService.grantDropsToUser(userId, record.getRewardId(), StatementSrc.TASK, record.getId());
            default:
                throw new UnsupportedOperationException();
        }
    }

    private boolean isTaskTriggered(Long userId) {
        return StringUtils.isNotBlank(redisUtil.getString(CacheKey.INSTANCE.taskTriggerWindow(userId)));
    }

    private void cacheTaskTriggered(Long userId) {
        redisUtil.setString(CacheKey.INSTANCE.taskTriggerWindow(userId), "Y", 1, TimeUnit.HOURS);
    }
}
