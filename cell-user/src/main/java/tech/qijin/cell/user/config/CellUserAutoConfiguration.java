package tech.qijin.cell.user.config;

import org.springframework.context.annotation.*;

/**
 * @author michealyang
 * @date 2019/1/8
 * 开始做眼保健操：←_← ↑_↑ →_→ ↓_↓
 **/
@Configuration
@ComponentScan(value = {"tech.qijin.cell.user.db", "tech.qijin.cell.user.service"})
//@Import(ImDatasourceConfig.class)
public class CellUserAutoConfiguration {
}
