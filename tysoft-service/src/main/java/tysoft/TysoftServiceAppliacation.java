package tysoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hxx
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan(value = {"com.tysoft.api.*","tysoft.server.*"})
@MapperScan("com.tysoft.api.mapper.*")
public class TysoftServiceAppliacation {
    public static void main(String[] args) {
        SpringApplication.run(TysoftServiceAppliacation.class, args);
    }
}
