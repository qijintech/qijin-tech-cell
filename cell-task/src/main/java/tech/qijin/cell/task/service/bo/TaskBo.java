package tech.qijin.cell.task.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.task.db.model.Task;
import tech.qijin.cell.task.db.model.TaskRecord;

@Data
@Builder
public class TaskBo {
    private Task task;
    private TaskRecord taskRecord;
    /**
     * 当前值
     */
    private Long curr;
}
