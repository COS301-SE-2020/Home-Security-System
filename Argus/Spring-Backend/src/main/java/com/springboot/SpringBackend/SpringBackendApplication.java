package com.springboot.SpringBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
@EnableJpaRepositories(basePackages={"com.*"}, entityManagerFactoryRef="emf")
 */

//@Controller
@SpringBootApplication
@EnableScheduling
public class SpringBackendApplication {

	//@RequestMapping("/")
	//@ResponseBody
	//String home() {
	//	return "";
	//s}

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}

}
