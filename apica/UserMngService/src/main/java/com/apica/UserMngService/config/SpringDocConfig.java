package com.apica.UserMngService.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 
* Swagger-UI: http://localhost:9889/swagger-ui/index.html
*/
@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi controllerApi() {
        return GroupedOpenApi.builder()
                .group("controller-api")
                .packagesToScan("com.apica.UserMngService.controller")
                .build();
    }
}

