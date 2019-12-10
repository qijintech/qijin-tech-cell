package tech.qijin.cell.gim.config;

import org.springframework.context.annotation.*;

/**
 * @author michealyang
 * @date 2019/1/8
 * 开始做眼保健操：←_← ↑_↑ →_→ ↓_↓
 **/
@Configuration
@ComponentScan(value = {"tech.qijin.cell.gim.db", "tech.qijin.cell.gim.service"})
@Import({CellGIMDatasourceConfig.class})
public class CellGIMAutoConfiguration {
}
