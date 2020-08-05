package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.SpringBackendApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

//@Service
public class RabbitConsumer {
   // private static final Logger log = LoggerFactory.getLogger(RabbitConsumer.class);

    //@RabbitListener(queues = SpringBackendApplication.DEFAULT_PARSING_QUEUE)
    //public void consumeDefaultMessage(final Message message) {
    //    log.info("Received message, tip is: {}", message.toString());
    //}
}
