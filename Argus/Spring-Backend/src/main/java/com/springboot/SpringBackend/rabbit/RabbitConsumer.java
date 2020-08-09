package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.model.*;
import com.springboot.SpringBackend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RabbitConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);

    /*private final NotificationService nservice;
    private final PersonService personService;
    private final UserService userService;
    private final FaceService faceService;
    private final ImageService imageService;

    @Autowired
    public RabbitConsumer(NotificationService ns, PersonService ps,
                          UserService us, FaceService fs, ImageService is) {
        this.nservice = ns;
        this.personService = ps;
        this.userService = us;
        this.faceService = fs;
        this.imageService = is;
    }*/

    //@RabbitListener(queues = {"alertQueue"})
    public void receivedAlert(RabbitAlert alert) {
        /*Long num = Long.valueOf(1);
        Optional<User> u =  userService.getUserById(num);

        Optional<Person> x =  personService.getPersonById(Long.valueOf(alert.getPid()));

        Image img = new Image(alert.getImageStr());
        imageService.createImage(img);

        nservice.createNotification(new Notification(img,
                "Intruder" + x.get().getFname() + " " + x.get().getLname(), u.get()));
        */
        LOGGER.info(String.valueOf(alert.getPid()));
        LOGGER.info("Alert Received");
    }

    //@RabbitListener(queues = {"personQueue"})
    public void receivedPerson(RabbitPerson newperson) {
        /*Image img = new Image(newperson.getImageStr());
        imageService.createImage(img);

        Person p = new Person(img);
        personService.createPerson(p);

        Face f = new Face(p, newperson.getFaceStr());
        faceService.createFace(f);
        */
        LOGGER.info(newperson.getImageStr());
        LOGGER.info("Person Created");
    }
}