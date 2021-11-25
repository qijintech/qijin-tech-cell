package tech.qijin.cell.aaaa.config;

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
@ComponentScan(value = {"tech.qijin.cell.aaaa.db", "tech.qijin.cell.aaaa.service", "tech.qijin.cell.aaaa.helper", "tech.qijin.cell.aaaa.server"})
@Import(CellaaaaDatasourceConfig.class)
public class CellaaaaAutoConfiguration {
    @PostConstruct
    public void init() {
        log.info("======= loading Cell aaaa =======");
    }
}
