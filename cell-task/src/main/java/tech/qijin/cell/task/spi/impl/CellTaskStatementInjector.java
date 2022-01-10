package tech.qijin.cell.task.spi.impl;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.spi.StatementInjector;
import tech.qijin.cell.account.spi.bo.StatementSrcBo;
import tech.qijin.cell.task.db.model.Task;
import tech.qijin.cell.task.helper.CellTaskHelper;

import java.util.Map;

@Slf4j
@Component
public class CellTaskStatementInjector extends StatementInjector {
    private Map<Long, StatementSrcBo> cache = Maps.newConcurrentMap();
    @Autowired
    private CellTaskHelper cellTaskHelper;

    @Override
    public StatementSrc getSrc() {
        return StatementSrc.TASK;
    }

    @Override
    public StatementSrcBo getName(Long dataId) {
        StatementSrcBo statementSrcBo = cache.get(dataId);
        if (statementSrcBo != null) return statementSrcBo;
        Task task = cellTaskHelper.getTask(dataId);
        if (task == null) return null;
        StatementSrcBo res = StatementSrcBo.builder()
                .src(getSrc())
                .name(task.getName())
                .build();
        cache.put(dataId, res);
        return res;
    }
}
