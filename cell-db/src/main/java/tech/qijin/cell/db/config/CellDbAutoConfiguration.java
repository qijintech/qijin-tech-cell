package tech.qijin.cell.db.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CellDbConfiguration.class)
public class CellDbAutoConfiguration {
}
