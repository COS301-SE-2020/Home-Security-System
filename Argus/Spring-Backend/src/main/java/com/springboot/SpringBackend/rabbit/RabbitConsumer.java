package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.model.*;
import com.springboot.SpringBackend.service.NotificationService;
import com.springboot.SpringBackend.service.PersonService;
import com.springboot.SpringBackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RabbitConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);

    private final NotificationService nservice;
    private final PersonService pservice;
    private final UserService uservice;

    @Autowired
    public RabbitConsumer(NotificationService ns, PersonService ps, UserService us) {
        this.nservice = ns;
        this.pservice = ps;
        this.uservice = us;
    }

    /*@RabbitListener(queues = {"alertQueue"})
    public void receivedAlert(RabbitAlert alert) {
        Long num = Long.valueOf(1);
        Optional<User> u =  uservice.getUserById(num);
        Optional<Person> x =  pservice.getPersonById(Long.valueOf(alert.getPid()));
        nservice.createNotification(new Notification(new Image(alert.getImage()),
                "Intruder" + x.get().getFname() + " " + x.get().getLname(), u.get()));
        LOGGER.info("Alert Received");
    }*/

    /*
    @RabbitListener(queues = {"persontQueue"})
    public void receivedPerson(RabbitPerson newperson) {
        pservice.createPerson(new Person(new Image(newperson.getImage()));
        LOGGER.info("Person Created");
    }
    */

}
