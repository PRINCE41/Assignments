package com.apica.JournalingService.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka.consumer")
public class CommonProp {

    @Setter@Getter
    private static ApplicationContext appCtx;
    private final ApplicationContext applicationContext;

    public CommonProp(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
 
    @PostConstruct
    public void init() {
        CommonProp.setAppCtx(applicationContext);
    }

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "kafka.consumer")
    public static class KafkaProp{
        private String bootstrapServers;
        private String groupId;
        private String autoOffsetReset;
        private String keyDeserializer;
        private String valueDeserializer;
        private String topicName;
    }

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "spring.redis")
    public static class RedisProp{
        private String host;
        private int port;
    }
    
}
