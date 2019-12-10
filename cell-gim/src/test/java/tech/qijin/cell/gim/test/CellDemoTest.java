package tech.qijin.cell.gim.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author UnitTest
 */

@SpringBootApplication(scanBasePackages = {"tech.qijin.cell.gim"})
@MapperScan("tech.qijin.cell.im.db.dao")
public class CellDemoTest {

    public static void main(String[] args) {
        SpringApplication.run(CellDemoTest.class, args);
    }
}
