package app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@Slf4j
public class AppRunner {
    public static void main(String[] args) {
        log.warn("APPLICATION STARTED");
        var ctx = SpringApplication.run(AppRunner.class, args);
//        System.out.println("///////////");
//        System.out.println(SpringProperties.getProperty("spring"));
//        System.out.println("///////////");
    }
}
