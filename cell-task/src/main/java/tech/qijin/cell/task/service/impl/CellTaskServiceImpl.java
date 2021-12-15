package tech.qijin.cell.task.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import tech.qijin.cell.task.base.TaskRecordStatus;
import tech.qijin.cell.task.db.model.Task;
import tech.qijin.cell.task.db.model.TaskRecord;
import tech.qijin.cell.task.helper.CellTaskHelper;
import tech.qijin.cell.task.service.CellTaskService;
import tech.qijin.util4j.aop.util.CasAssert;

import java.util.List;

@Slf4j
@Service
public class CellTaskServiceImpl implements CellTaskService {
    @Autowired
    private CellTaskService cellTaskService;
    @Autowired
    private CellTaskHelper cellTaskHelper;

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
    public void claimTaskReward(Long userId, Long taskRecordId) {

    }
}
