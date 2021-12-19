package tysoft.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author hxx
 */
@Configuration
@EnableSwagger2  //开启Swagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("泰源软件", "", "");
        return new ApiInfo("泰源软件的API文档",
                "主项目",
                "V1.0",
                "",
                contact,
                "",
                "",
                new ArrayList());
    }
}
