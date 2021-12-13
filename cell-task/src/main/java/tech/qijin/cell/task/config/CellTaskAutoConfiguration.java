package tech.qijin.cell.task.config;

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
@ComponentScan(value = {"tech.qijin.cell.task.db", "tech.qijin.cell.task.service", "tech.qijin.cell.task.helper", "tech.qijin.cell.task.server"})
@Import(CellTaskDatasourceConfig.class)
public class CellTaskAutoConfiguration {
    @PostConstruct
    public void init() {
        log.info("======= loading Cell task =======");
    }
}
