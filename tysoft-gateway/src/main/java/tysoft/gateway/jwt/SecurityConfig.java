package tysoft.gateway.jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @Description:
 * @Author: hxx
 * @Date: 2021/12/17
 **/
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig{
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable();
        return http.build();
    }
}