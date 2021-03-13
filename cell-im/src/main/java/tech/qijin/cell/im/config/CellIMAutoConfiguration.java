package tech.qijin.cell.im.config;

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
@ComponentScan(value = {"tech.qijin.cell.im.db", "tech.qijin.cell.im.service", "tech.qijin.cell.im.helper"})
@Import(CellIMDatasourceConfig.class)
public class CellIMAutoConfiguration {
    @PostConstruct
    public void init() {
        log.info("======= loading Cell IM =======");
    }
}
