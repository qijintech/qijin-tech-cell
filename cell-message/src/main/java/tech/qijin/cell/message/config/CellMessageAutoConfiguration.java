package tech.qijin.cell.message.config;

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
@ComponentScan(value = {"tech.qijin.cell.message.db", "tech.qijin.cell.message.service", "tech.qijin.cell.message.helper", "tech.qijin.cell.message.server"})
@Import(CellMessageDatasourceConfig.class)
public class CellMessageAutoConfiguration {
    @PostConstruct
    public void init() {
        log.info("======= loading Cell message =======");
    }
}
