package com.springboot.SpringBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// @EnableJpaRepositories(basePackages={"com.*"}, entityManagerFactoryRef="emf")
@EnableScheduling
@EnableWebMvc
@SpringBootApplication
public class SpringBackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}
}
