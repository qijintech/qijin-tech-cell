package tech.qijin.cell.task.helper.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.qijin.cell.task.base.TaskRecordStatus;
import tech.qijin.cell.task.db.dao.TaskDao;
import tech.qijin.cell.task.db.dao.TaskRecordDao;
import tech.qijin.cell.task.db.model.Task;
import tech.qijin.cell.task.db.model.TaskExample;
import tech.qijin.cell.task.db.model.TaskRecord;
import tech.qijin.cell.task.db.model.TaskRecordExample;
import tech.qijin.cell.task.helper.CellTaskHelper;
import tech.qijin.util4j.trace.pojo.Channel;
import tech.qijin.util4j.trace.util.ChannelUtil;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.NumberUtil;
import tech.qijin.util4j.web.pojo.LocalCacheAgent;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellTaskHelperImpl implements CellTaskHelper {
    private LocalCacheAgent tasksCache = new LocalCacheAgent<Task>();
    private Set<String> countingCodeSet = Sets.newHashSet();
    private Date lastUpdateAt = Date.from(Instant.EPOCH);
    private Map<String, List<Task>> countingCodeTasksMap = Maps.newHashMap();
    private Map<Long, Task> taskMap = Maps.newHashMap();

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TaskRecordDao taskRecordDao;

    @PostConstruct
    @Scheduled(fixedDelay = 5000)
    public void reload() {
        ChannelUtil.setChannel(Channel.TEST);
        tasksCache.process(
                () -> taskDao.getLastUpdatedAt(),
                () -> listTaskFromDB())
                .callback((Consumer<List<Task>>) tasks -> {
                    countingCodeSet = tasks.stream()
                            .map(Task::getCountingCode)
                            .collect(Collectors.toSet());
                    countingCodeTasksMap = tasks.stream()
                            .collect(Collectors.groupingBy(Task::getCountingCode));
                    taskMap = tasks.stream().collect(Collectors.toMap(Task::getId, Function.identity()));
                });
    }

    @Override
    public List<Task> listAllTask() {
        if (CollectionUtils.isNotEmpty(tasksCache.get())) return tasksCache.get();
        return listTaskFromDB();
    }

    @Override
    public Task getTask(Long taskId) {
        return taskMap.get(taskId);
    }

    @Override
    public Map<Long, Task> mapAllTask() {
        return listAllTask().stream().collect(Collectors.toMap(Task::getId, Function.identity()));
    }

    @Override
    public Set<String> listCountingCode() {
        return countingCodeSet;
    }

    @Override
    public Map<String, List<Task>> mapCountingCodeAndTasks() {
        return countingCodeTasksMap;
    }

    @Override
    public List<Task> listTasksByCountingCode(String countingCode) {
        return countingCodeTasksMap.get(countingCode);
    }

    @Override
    public boolean insertTaskRecord(TaskRecord record) {
        String checkRes = checkTaskRecord(record);
        if (StringUtils.isNotBlank(checkRes)) {
            log.error("insertTaskRecord invalid param, res={}, record={}", checkRes, record);
            return false;
        }
        return taskRecordDao.insertSelective(record) > 0;
    }

    @Override
    public TaskRecord getTaskRecord(Long userId, Long taskId) {
        TaskRecordExample example = new TaskRecordExample();
        example.setOrderByClause("start_time desc limit 1");
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andTaskIdEqualTo(taskId);
        return taskRecordDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public TaskRecord getTaskRecord(Long taskRecordId) {
        return taskRecordDao.selectByPrimaryKey(taskRecordId);
    }

    @Override
    public List<TaskRecord> listTaskRecord(Long userId) {
        Date now = DateUtil.now();
        TaskRecordExample example = new TaskRecordExample();
        example.createCriteria().andUserIdEqualTo(userId)
                .andStartTimeLessThanOrEqualTo(now)
                .andEndTimeGreaterThanOrEqualTo(now);
        return taskRecordDao.selectByExample(example);
    }

    @Override
    public boolean updateTaskStatus(TaskRecord record, TaskRecordStatus toStatus) {
        if (!record.getStatus().isFlowableTo(toStatus)) {
            log.warn("updateTaskStatus invalid toStatus, recordId={}, fromStatus={}, toStatus={}", record.getId(), record.getStatus(), toStatus);
            return false;
        }
        TaskRecord update = new TaskRecord();
        update.setStatus(toStatus);
        update.setVersion(record.getVersion() + 1);
        TaskRecordExample example = new TaskRecordExample();
        example.createCriteria()
                .andIdEqualTo(record.getId())
                .andVersionEqualTo(record.getVersion());
        return taskRecordDao.updateByExampleSelective(update, example) > 0;
    }

    public String getTaskFormat(Long userId, Task task) {
        Date now = DateUtil.now();
        switch (task.getKind()) {
            case ONCE:
                return String.format("task:once:%d:%d", task.getId(), userId);
            case DAILY:
                return String.format("task:daily:%d:%d:%s", task.getId(), userId, DateUtil.formatStr(now, "yyyyMMdd"));
            case WEEKLY:
                return String.format("task:weekly:%d:%d:%s", task.getId(), userId, DateUtil.getCurrentWeek());
            case MONTHLY:
                return String.format("task:monthly:%d:%d:%s", task.getId(), userId, DateUtil.formatStr(now, "yyyyMM"));
            case YEARLY:
                return String.format("task:yearly:%d:%d:%s", task.getId(), userId, DateUtil.formatStr(now, "yyyy"));
            default:
                return "";
        }
    }

    private List<Task> listTaskFromDB() {
        TaskExample example = new TaskExample();
        return taskDao.selectByExample(example);
    }

    private String checkTaskRecord(TaskRecord record) {
        if (!NumberUtil.gtZero(record.getUserId())) return "userId";
        if (!NumberUtil.gtZero(record.getTaskId())) return "taskId";
        if (!NumberUtil.gtZero(record.getTarget())) return "target";
        if (StringUtils.isBlank(record.getCountingCode())) return "countingCode";
        if (StringUtils.isBlank(record.getTaskFormat())) return "taskFormat";
        if (record.getStartTime() == null) return "startTime";
        if (record.getEndTime() == null) return "endTime";
        return "";
    }
}
