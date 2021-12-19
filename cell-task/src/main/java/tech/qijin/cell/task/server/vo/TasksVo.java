package tech.qijin.cell.task.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TasksVo {
    private List<TaskVo> tasks;
}
