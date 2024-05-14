package com.apica.UserMngService.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConf {

    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return (beanFactory) -> {
            beanFactory.getBeanDefinition("adminSeeder").setDependsOn("roleSeeder");
        };
    }

}
