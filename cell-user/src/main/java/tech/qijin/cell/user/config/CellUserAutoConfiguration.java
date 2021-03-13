package tech.qijin.cell.user.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;

/**
 * @author michealyang
 * @date 2019/1/8
 * 开始做眼保健操：←_← ↑_↑ →_→ ↓_↓
 **/
@Slf4j
@Configuration
@ComponentScan(value = {"tech.qijin.cell.user.db", "tech.qijin.cell.user.service", "tech.qijin.cell.user.helper"})
@Import(CellUserDatasourceConfig.class)
public class CellUserAutoConfiguration {
    @PostConstruct
    public void init() {
        log.info("======= loading Cell User =======");
    }
}
