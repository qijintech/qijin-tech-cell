package tech.qijin.cell.im.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author michealyang
 * @date 2019/1/8
 * 开始做眼保健操：←_← ↑_↑ →_→ ↓_↓
 **/
@Configuration
@ComponentScan(basePackages = "tech.qijin.cell.im")
@MapperScan("tech.qijin.cell.im.db.dao")
public class CellIMAutoConfiguration {
}
