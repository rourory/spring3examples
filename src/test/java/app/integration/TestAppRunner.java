package app.integration;

import app.database.ConnectionPool;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestAppRunner {

    @MockBean(name="connectionPool")
    private ConnectionPool connectionPool;

}
