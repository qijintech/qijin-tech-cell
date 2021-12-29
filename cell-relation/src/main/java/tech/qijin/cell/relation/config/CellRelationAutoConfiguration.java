package tech.qijin.cell.relation.config;

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
@ComponentScan(value = {"tech.qijin.cell.relation.db", "tech.qijin.cell.relation.service", "tech.qijin.cell.relation.helper", "tech.qijin.cell.relation.server"})
@Import(CellRelationDatasourceConfig.class)
public class CellRelationAutoConfiguration {
    @PostConstruct
    public void init() {
        log.info("======= loading Cell relation =======");
    }
}
