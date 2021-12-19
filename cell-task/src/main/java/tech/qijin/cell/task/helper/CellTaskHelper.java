package tech.qijin.cell.task.helper;

import tech.qijin.cell.task.base.TaskRecordStatus;
import tech.qijin.cell.task.db.model.Task;
import tech.qijin.cell.task.db.model.TaskRecord;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CellTaskHelper {
    /**
     * 返回所有的Task
     *
     * @return
     */
    List<Task> listAllTask();

    Map<Long, Task> mapAllTask();

    /**
     * 返回Task中用到的countingCode
     *
     * @return
     */
    Set<String> listCountingCode();

    /**
     * 返回countingCode和关联Tasks的映射
     *
     * @return
     */
    Map<String, List<Task>> mapCountingCodeAndTasks();

    /**
     * 与 {@link #mapCountingCodeAndTasks()} 类似
     *
     * @param countingCode
     * @return
     */
    List<Task> listTasksByCountingCode(String countingCode);

    /**
     * 创建任务记录
     * @param record
     * @return
     */
    boolean insertTaskRecord(TaskRecord record);
    /**
     * 获取任务记录
     *
     * @param userId
     * @param taskId
     * @return
     */
    TaskRecord getTaskRecord(Long userId, Long taskId);

    /**
     * 获取用户任务列表
     * @param userId
     * @return
     */
    List<TaskRecord> listTaskRecord(Long userId);

    /**
     * 更改任务状态
     *
     * @param record
     * @param toStatus
     * @return
     */
    boolean updateTaskStatus(TaskRecord record, TaskRecordStatus toStatus);

    String getTaskFormat(Long userId, Task task);
}
