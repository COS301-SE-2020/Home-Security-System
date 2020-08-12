package com.springboot.SpringBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
@EnableJpaRepositories(basePackages={"com.*"}, entityManagerFactoryRef="emf")
 */
@SpringBootApplication
@EnableScheduling
public class SpringBackendApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SpringBackendApplication.class, args);
	}

}
