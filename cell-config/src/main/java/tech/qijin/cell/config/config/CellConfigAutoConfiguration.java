package tech.qijin.cell.config.config;

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
@ComponentScan(value = {"tech.qijin.cell.config.db", "tech.qijin.cell.config.service", "tech.qijin.cell.config.helper", "tech.qijin.cell.config.server"})
public class CellConfigAutoConfiguration {
    @PostConstruct
    public void init() {
        log.info("======= loading Cell config =======");
    }
}
