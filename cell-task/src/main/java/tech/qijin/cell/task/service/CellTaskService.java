package tech.qijin.cell.task.service;

import tech.qijin.cell.task.db.model.Task;
import tech.qijin.cell.task.db.model.TaskRecord;
import tech.qijin.cell.task.service.bo.TaskBo;

import java.util.List;

public interface CellTaskService {

    /**
     * 触发任务
     *
     * @param userId
     */
    void triggerTask(Long userId);

    /**
     * 查看当前能看到的任务
     *
     * @return
     */
    List<TaskBo> listUserTask(Long userId);

    /**
     * 可领取的第一个任务
     * @param userId
     * @return
     */
    TaskBo getClaimableTask(Long userId);

    /**
     * 完成任务，根据countingCode
     *
     * @param userId
     * @param countingCode
     */
    void onFinishTask(Long userId, String countingCode);

    /**
     * 完成任务，根据Task
     *
     * @param userId
     * @param task
     */
    void onFinishTask(Long userId, Task task);

    /**
     * 批量完成任务
     *
     * @param userId
     * @param tasks
     */
    void onFinishTasks(Long userId, List<Task> tasks);

    /**
     * 任务领奖
     *
     * @param userId
     * @param taskRecordId
     */
    void claimTaskReward(Long userId, Long taskRecordId);
}
