package tech.qijin.cell.task.helper.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellTaskHelperImpl implements CellTaskHelper {
    private List<Task> tasks = Lists.newArrayList();
    private Set<String> countingCodeSet = Sets.newHashSet();
    private Date lastUpdateAt = Date.from(Instant.EPOCH);
    private Map<String, List<Task>> countingCodeTasksMap = Maps.newHashMap();

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TaskRecordDao taskRecordDao;

    @PostConstruct
    @Scheduled(fixedDelay = 5000)
    public void reload() {
        ChannelUtil.setChannel(Channel.TEST);
        Date updateAt = taskDao.getLastUpdatedAt();
        if (null == updateAt
                || updateAt.after(lastUpdateAt)
                || Math.abs(DateUtil.getMinusSeconds(DateUtil.now(), updateAt).longValue()) < 10) {
            tasks = listTaskFromDB();
            countingCodeSet = tasks.stream()
                    .map(Task::getCountingCode)
                    .collect(Collectors.toSet());
            countingCodeTasksMap = tasks.stream()
                    .collect(Collectors.groupingBy(Task::getCountingCode));
            lastUpdateAt = updateAt;
        }
    }

    @Override
    public List<Task> listTasks() {
        if (CollectionUtils.isNotEmpty(tasks)) return tasks;
        return listTaskFromDB();
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
    public TaskRecord getTaskRecord(Long userId, Long taskId) {
        TaskRecordExample example = new TaskRecordExample();
        example.setOrderByClause("start_time desc limit 1");
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andTaskIdEqualTo(taskId);
        return taskRecordDao.selectByExample(example).stream().findFirst().orElse(null);
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

    private List<Task> listTaskFromDB() {
        TaskExample example = new TaskExample();
        return taskDao.selectByExample(example);
    }
}
