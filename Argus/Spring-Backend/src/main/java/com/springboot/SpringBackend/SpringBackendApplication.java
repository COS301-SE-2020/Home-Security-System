package com.springboot.SpringBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// @EnableJpaRepositories(basePackages={"com.*"}, entityManagerFactoryRef="emf")
// @EnableWebMvc
//@Controller
@EnableScheduling
@SpringBootApplication
public class SpringBackendApplication extends SpringBootServletInitializer {

	//@RequestMapping("/")
	//@ResponseBody
	//public String home() {
	//	return "Hello World!";
	//}

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}
}
