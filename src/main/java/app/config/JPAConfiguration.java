package app.config;

import app.config.condition.JpaCondition;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Conditional(JpaCondition.class)
@Configuration
public class JPAConfiguration {


    @PostConstruct
    void init() {
        log.info("JPAConfiguration is enabled");
    }
}
