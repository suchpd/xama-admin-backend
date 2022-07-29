package com.xama.backend.api.configurations;

import com.xama.backend.infrastructure.mediatr.core.Mediator;
import com.xama.backend.infrastructure.mediatr.core.Registry;
import com.xama.backend.infrastructure.mediatr.spring.SpringMediator;
import com.xama.backend.infrastructure.mediatr.spring.SpringRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringMediatorConfig {

    private final ApplicationContext applicationContext;

    public SpringMediatorConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public Registry registry() {
        return new SpringRegistry(applicationContext);
    }
    @Bean
    public Mediator mediator(Registry registry) {
        return new SpringMediator(registry);
    }

}
