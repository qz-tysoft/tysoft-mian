package tysoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hxx
 */
@SpringBootApplication
@ComponentScan(value = {"com.tysoft.api.*","tysoft.gateway.*"})
@MapperScan("com.tysoft.api.mapper.*")
public class TysoftGateWayAppliacation {
    public static void main(String[] args) {
        SpringApplication.run(TysoftGateWayAppliacation.class, args);
    }
}
