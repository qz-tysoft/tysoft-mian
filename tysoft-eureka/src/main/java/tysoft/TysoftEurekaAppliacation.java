package tysoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author hxx eurekaServer
 * @date 2021/12/23
 */
@SpringBootApplication
@EnableEurekaServer
public class TysoftEurekaAppliacation {
    public static void main(String[] args) {
        SpringApplication.run(TysoftEurekaAppliacation.class, args);
    }
}
