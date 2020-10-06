package com.springboot.SpringBackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Controller
    static class Routes {
        @RequestMapping(value = "/api/**", method = RequestMethod.GET)
        public String index() {
            return "index.html";
            //return "forward:/";
        }
    }
}
