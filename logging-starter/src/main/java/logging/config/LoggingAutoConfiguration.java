package logging.config;

import jakarta.annotation.PostConstruct;
import logging.aop.CommonPointcut;
import logging.aop.FirstAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(LoggingProps.class)
@ConditionalOnClass(LoggingProps.class)
@ConditionalOnProperty(prefix = "app.common.logging", name = "enabled", havingValue = "true")
public class LoggingAutoConfiguration {

    @PostConstruct
    void init(){
        log.warn("LoggingAutoConfiguration has just been initialized");
    }

    @Bean
    public CommonPointcut commonPointcut(){
        return new CommonPointcut();
    }

    @Bean
    public FirstAspect firstAspect(){
        return new FirstAspect();
    }
}
