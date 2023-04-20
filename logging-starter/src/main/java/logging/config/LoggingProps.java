package logging.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@Slf4j
@ConfigurationProperties(prefix = "app.common.logging")
public class LoggingProps {
    /**
     * to enable common logging aop on service layer
     */
    private boolean enabled;

    /**
     * to specify logging level
     */
    private String level;

    @PostConstruct
    void init(){
        log.info("Logging props are configured nad initialized: {}", this);
    }
}
