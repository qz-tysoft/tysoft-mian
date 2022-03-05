package tysoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hxx
 */
@SpringBootApplication
@ComponentScan(value = {"com.tysoft.api.*","tysoft.auth.*"})
@MapperScan("com.tysoft.api.mapper.*")
@EnableEurekaClient
public class TysoftAuthAppliacation {
    public static void main(String[] args) {
        SpringApplication.run(TysoftAuthAppliacation.class, args);
    }
}
