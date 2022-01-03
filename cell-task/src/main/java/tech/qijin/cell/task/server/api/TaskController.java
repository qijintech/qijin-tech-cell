package tech.qijin.cell.task.server.api;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.qijin.cell.account.db.model.DropsItem;
import tech.qijin.cell.account.server.vo.DropsItemVo;
import tech.qijin.cell.account.service.CellDropsService;
import tech.qijin.cell.account.service.bo.DropsBo;
import tech.qijin.cell.task.base.TaskRecordStatus;
import tech.qijin.cell.task.db.model.TaskRecord;
import tech.qijin.cell.task.server.vo.TaskReqVo;
import tech.qijin.cell.task.server.vo.TaskVo;
import tech.qijin.cell.task.server.vo.TasksVo;
import tech.qijin.cell.task.service.CellTaskService;
import tech.qijin.cell.task.service.bo.TaskBo;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.AsyncUtil;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    @Autowired
    private CellTaskService cellTaskService;
    @Autowired
    private CellDropsService cellDropsService;

    @GetMapping("/list")
    public TasksVo listTask() {
        List<TaskBo> taskBos = cellTaskService.listUserTask(UserUtil.getUserId());
        TasksVo tasksVo = TasksVo.builder()
                .tasks(TaskVo.from(taskBos))
                .build();
        if (tasksVo != null && CollectionUtils.isNotEmpty(tasksVo.getTasks())) {
            tasksVo.getTasks().stream()
                    .filter(Objects::nonNull)
                    .forEach(taskVo -> {
                        DropsBo dropsBo = cellDropsService.getDropsDetail(taskVo.getRewardId());
                        if (taskVo != null && dropsBo != null) {
                            taskVo.setDropsItems(DropsItemVo.from(dropsBo.getDropsItemBos()));
                        }
                    });
        }
        return tasksVo;
    }

    @GetMapping("/claimable")
    public TaskVo claimableTask() {
        TaskBo taskBo = cellTaskService.getClaimableTask(UserUtil.getUserId());
        if (taskBo == null) {
            cellTaskService.triggerTask(UserUtil.getUserId());
            taskBo = cellTaskService.getClaimableTask(UserUtil.getUserId());
        }
        DropsBo dropsBo = cellDropsService.getDropsDetail(taskBo.getTaskRecord().getRewardId());
        TaskVo taskVo = TaskVo.from(taskBo);
        if (taskVo != null && dropsBo != null) {
            taskVo.setDropsItems(DropsItemVo.from(dropsBo.getDropsItemBos()));
        }
        return taskVo;
    }

    @PostMapping("/claim")
    public TaskVo doClaim(@RequestBody TaskReqVo reqVo) {
        MAssert.notNull(reqVo, ResEnum.INVALID_PARAM);
        MAssert.isTrue(NumberUtil.gtZero(reqVo.getTaskId()), ResEnum.INVALID_PARAM);
        DropsBo dropsBo = cellTaskService.claimTaskReward(UserUtil.getUserId(), reqVo.getTaskId());
        MAssert.isTrue(dropsBo != null, ResEnum.INTERNAL_ERROR.code, "领取失败");
        return TaskVo.builder()
                .dropsItems(DropsItemVo.from(dropsBo.getDropsItemBos()))
                .build();
    }
}
