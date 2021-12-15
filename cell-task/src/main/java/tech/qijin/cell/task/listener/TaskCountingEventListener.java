package tech.qijin.cell.task.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tech.qijin.cell.task.helper.CellTaskHelper;
import tech.qijin.cell.task.service.CellTaskService;
import tech.qijin.util4j.lang.event.CountingEvent;

@Slf4j
@Component
public class TaskCountingEventListener {
    @Autowired
    private CellTaskHelper cellTaskHelper;
    @Autowired
    private CellTaskService cellTaskService;

    @EventListener
    public void onCountingReachTarget(CountingEvent countingEvent) {
        // 首先检查下counting回调的code是否是Task里面用到的，没有用到的无需关心
        if (!cellTaskHelper.listCountingCode().contains(countingEvent.getCountingCode())) {
            return;
        }
        log.info("onCountingReachTarget, event={}", countingEvent);
        // 说明Task完成了
        cellTaskService.onFinishTask(countingEvent.getUserId());
    }
}
