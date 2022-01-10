package tech.qijin.cell.account.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * @author michealyang
 * @date 2019/1/8
 * 开始做眼保健操：←_← ↑_↑ →_→ ↓_↓
 **/
@Slf4j
@Configuration
@ComponentScan(value = {"tech.qijin.cell.account.db",
        "tech.qijin.cell.account.service",
        "tech.qijin.cell.account.helper",
        "tech.qijin.cell.account.server",
        "tech.qijin.cell.account.spi"})
@Import(CellAccountDatasourceConfig.class)
public class CellAccountAutoConfiguration {
    @PostConstruct
    public void init() {
        log.info("======= loading Cell account =======");
    }
}
