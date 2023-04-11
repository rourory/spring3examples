package app.config;

import app.AppRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableEnversRepositories(basePackageClasses = AppRunner.class)
public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorAware(){
//         SecurityContext.getCurrentUser.getUsername();
        return () -> Optional.of("sqlead");
    }

}
