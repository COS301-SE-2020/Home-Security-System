package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {
    static final Logger LOGGER = LoggerFactory.getLogger(RabbitProducer.class);
    private AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitProducer(AmqpTemplate template) {
        this.amqpTemplate = template;
    }

    /*
    //@Scheduled(fixedDelay = 10000L)
    public void sendAlert() {
        RabbitAlert x = new RabbitAlert(Long.valueOf(1), "Grey", "Intruder");
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ALERT_KEY, x);
        LOGGER.info("Alert Sent");
    }
    */

    /*
    //@Scheduled(fixedDelay = 10000L)
    public void sendPerson() {
        RabbitPerson x = new RabbitPerson(Long.valueOf(1), "Grey", "Feature", "Photo", true);
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.PERSON_KEY, x);
        LOGGER.info("Person Sent");
    }
    */

    /*
    //@Scheduled(fixedDelay = 10000L)
    public void sendFeature() {
        RabbitPerson x = new RabbitPerson(Long.valueOf(1), "Grey", "Feature", "Photo", true);
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.FEATURE_KEY, x);
        LOGGER.info("Feature Sent");
    }
    */
}
