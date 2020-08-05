package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.SpringBackendApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class RabbitProducer {
    // static final Logger log = LoggerFactory.getLogger(RabbitProducer.class);

    //private final RabbitTemplate rabbitTemplate;

    //@Autowired
    //public RabbitProducer(final RabbitTemplate rabbitTemplate) {
    //    this.rabbitTemplate = rabbitTemplate;
    //}

    //@Scheduled(fixedDelay = 3000L)
    //public void sendPracticalTip() {
    //    RabbitNotification x = new RabbitNotification("Intruder", 1);
    //    rabbitTemplate.convertAndSend(SpringBackendApplication.EXCHANGE_NAME, SpringBackendApplication.ROUTING_KEY, x);
    //    log.info("Message sent");
    //}
}
