package app.config;

import app.database.ConnectionPool;
import org.springframework.context.annotation.*;
import web.WebConfiguration;

@Import(WebConfiguration.class)
@Configuration
public class ApplicationConfiguration {
    @Bean
    public ConnectionPool pool2() {
        return new ConnectionPool("FromConfigUsername2", 666);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("FromConfigUsername3", 666);
    }


//    @Profile("prod")
//    public UserRepository userRepository2(@Qualifier("pool2") ConnectionPool pool2) {
//        return new UserRepository(pool2, 23);
//    }

/*    @Bean
    public UserRepository userRepository3() {
        return new UserRepository(pool3(), 43);
    }
*/
}
