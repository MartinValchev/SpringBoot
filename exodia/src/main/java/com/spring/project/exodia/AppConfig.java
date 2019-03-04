package com.spring.project.exodia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.project.exodia.utils.UuIdUserGenerator;

@Configuration
public class AppConfig {
    @Bean
    public UuIdUserGenerator uuIdGenerator() {
        return new UuIdUserGenerator();
    }
}
