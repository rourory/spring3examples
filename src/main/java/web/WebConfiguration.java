package web;

import app.database.ConnectionPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("web")
@Slf4j
public class WebConfiguration {

    @Bean
    public ConnectionPool webPool(){
        log.info("WebPool is created");
        return new ConnectionPool("webPool", 1);
    }
}
