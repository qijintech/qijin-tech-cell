package tech.qijin.cell.account.spi;

import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.spi.bo.StatementSrcBo;

import javax.annotation.PostConstruct;

public abstract class StatementInjector {
    @Autowired
    private CellStatementSpi cellStatementSpi;

    @PostConstruct
    public void init() {
        cellStatementSpi.register(getSrc(), this);
    }

    public abstract StatementSrc getSrc();

    public abstract StatementSrcBo getName(Long dataId);
}
