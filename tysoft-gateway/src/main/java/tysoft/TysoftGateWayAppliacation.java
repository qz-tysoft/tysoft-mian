package tysoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hxx
 */
@SpringBootApplication
@ComponentScan(value = {"tysoft.gateway.*"})
public class TysoftGateWayAppliacation {
    public static void main(String[] args) {
        SpringApplication.run(TysoftGateWayAppliacation.class, args);
    }
}
