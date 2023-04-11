package app.database;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ToString
public class ConnectionPool {

    private final String username;

    private final int poolSize;

    public ConnectionPool(@Value("${db.username}") String username, @Value("${db.pool.size}") int poolSize) {
        this.username = username;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        log.info("Init connection pool");
    }

    @PreDestroy
    private void destroy() {
        log.info("Clean connection pool");
    }
}
