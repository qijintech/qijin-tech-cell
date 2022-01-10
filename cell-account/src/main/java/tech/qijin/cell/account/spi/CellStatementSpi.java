package tech.qijin.cell.account.spi;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.spi.bo.StatementSrcBo;

import java.util.Map;

@Slf4j
@Component
public class CellStatementSpi {
    private Map<StatementSrc, StatementInjector> injectorMap = Maps.newHashMap();

    public void register(StatementSrc src, StatementInjector injector) {
        injectorMap.put(src, injector);
    }

    public StatementSrcBo getSrcBo(StatementSrc src, Long dataId) {
        StatementInjector injector = injectorMap.get(src);
        if (injector == null) {
            return StatementSrcBo.defaultBo(src);
        }
        return injector.getName(dataId);
    }
}
