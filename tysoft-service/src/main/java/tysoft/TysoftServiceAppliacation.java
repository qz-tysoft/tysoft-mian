package tysoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import tysoft.server.security.SecurityConfig;

/**
 * @author hxx
 */
@SpringBootApplication
@ComponentScan(value = {"com.tysoft.api.*","tysoft.server.*"})
@MapperScan("com.tysoft.api.mapper.*")
@EnableEurekaClient
public class TysoftServiceAppliacation {
    public static void main(String[] args) {
        SpringApplication.run(TysoftServiceAppliacation.class, args);
    }
}
