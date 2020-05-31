package tech.qijin.cell.user.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author UnitTest
 */

@SpringBootApplication(scanBasePackages = {"tech.qijin.cell.user"})
@MapperScan("tech.qijin.cell.user.db.dao")
public class CellDemoTest {

    public static void main(String[] args) {
        SpringApplication.run(CellDemoTest.class, args);
    }
}
