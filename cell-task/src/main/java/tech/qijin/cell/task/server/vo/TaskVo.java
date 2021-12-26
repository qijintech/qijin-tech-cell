package tech.qijin.cell.task.server.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.account.server.vo.DropsItemVo;
import tech.qijin.cell.task.base.RewardType;
import tech.qijin.cell.task.base.TaskRecordStatus;
import tech.qijin.cell.task.db.model.Task;
import tech.qijin.cell.task.db.model.TaskRecord;
import tech.qijin.cell.task.service.bo.TaskBo;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class TaskVo {
    private Long id;

    private String name;

    private Long curr;

    private Long target;

    private RewardType rewardType;

    private Long rewardId;

    private Date startTime;

    private Date endTime;

    private TaskRecordStatus status;

    private Long percent;

    private String forwardText;

    private String forward;

    private List<DropsItemVo> dropsItems;

    public static TaskVo from(TaskBo taskBo) {
        if (taskBo == null) return null;
        Task task = taskBo.getTask();
        TaskRecord record = taskBo.getTaskRecord();
        Long curr = taskBo.getCurr();
        if (!TaskRecordStatus.PROCESSING.equals(record.getStatus())) {
            curr = task.getTarget();
        }
        return TaskVo.builder()
                .id(record.getId())
                .name(task.getName())
                .target(task.getTarget())
                .curr(curr)
                .rewardType(record.getRewardType())
                .rewardId(record.getRewardId())
                .startTime(record.getStartTime())
                .endTime(record.getEndTime())
                .status(record.getStatus())
                .percent(curr / record.getTarget() * 100)
                .forwardText(task.getForwardText())
                .forward(task.getForward())
                .build();
    }

    public static List<TaskVo> from(List<TaskBo> taskBos) {
        if (CollectionUtils.isEmpty(taskBos)) return Collections.emptyList();
        return taskBos.stream().map(TaskVo::from).collect(Collectors.toList());
    }
}
