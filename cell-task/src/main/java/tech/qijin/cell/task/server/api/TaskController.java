package tech.qijin.cell.task.server.api;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.cell.task.base.TaskRecordStatus;
import tech.qijin.cell.task.db.model.TaskRecord;
import tech.qijin.cell.task.server.vo.TaskVo;
import tech.qijin.cell.task.server.vo.TasksVo;
import tech.qijin.cell.task.service.CellTaskService;
import tech.qijin.cell.task.service.bo.TaskBo;
import tech.qijin.util4j.utils.AsyncUtil;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    @Autowired
    private CellTaskService cellTaskService;

    @GetMapping("/list")
    public TasksVo listTask() {
        List<TaskBo> taskBos = cellTaskService.listUserTask(UserUtil.getUserId());
        return TasksVo.builder()
                .tasks(TaskVo.from(taskBos))
                .build();
    }

    @GetMapping("/claimable")
    public TaskVo claimableTask() {
        TaskBo taskBo = cellTaskService.getClaimableTask(UserUtil.getUserId());
        return TaskVo.from(taskBo);
    }
}
