package com.springboot.SpringBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

// @EnableJpaRepositories(basePackages={"com.*"}, entityManagerFactoryRef="emf")
// @EnableWebMvc
@EnableScheduling
@SpringBootApplication
public class SpringBackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}
}
