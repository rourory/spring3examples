package app.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "db")
public class DatabaseProperties {
    private String username;
    private String password;
    private String driver;
    private String url;
    private String hosts;
    private PoolProperties pool;
    private List<PoolProperties> pools;
    private Map<String, Object> properties;

    @Data
    @NoArgsConstructor
    public static class PoolProperties {
        private Integer size;
        private Integer timeout;

    }
}
