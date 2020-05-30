package tech.qijin.cell.im.config;

import org.springframework.context.annotation.*;

/**
 * @author michealyang
 * @date 2019/1/8
 * 开始做眼保健操：←_← ↑_↑ →_→ ↓_↓
 **/
@Configuration
@ComponentScan(value = {"tech.qijin.cell.im.db", "tech.qijin.cell.im.service", "tech.qijin.cell.im.helper"})
@Import(CellIMDatasourceConfig.class)
public class CellIMAutoConfiguration {
}
