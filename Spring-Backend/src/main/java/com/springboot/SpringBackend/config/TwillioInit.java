package com.springboot.SpringBackend.config;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwillioInit {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwillioInit.class);
    public final TwillioConfig twillioConfig;

    @Autowired
    public TwillioInit(TwillioConfig twillioConfig) {
        this.twillioConfig = twillioConfig;
        Twilio.init(twillioConfig.getAccountId(), twillioConfig.getToken());
        LOGGER.info("Twilio was successfully initialized");
    }
}
