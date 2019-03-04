package com.spring.project.exodia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.spring.project.exodia.repository")
@EntityScan("com.spring.project.exodia.entity")
public class ExodiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExodiaApplication.class, args);
	}

}
