package com.floppylab.salgotarjanschedule.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo())
                .forCodeGeneration(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Salgótarján helyi járati menetrend",
                "Salgótarján helyi járati menetrend alkalmazás REST APIja",
                "1.0.0",
                "TODO",
                new Contact("Dér Leonóra", "https://floppylab.com", "leonora.der@floppylab.com"),
                "MIT", "https://github.com/floppylab/salgotarjan-menetrend-backend/blob/master/LICENSE", Collections.emptyList());
    }
}
