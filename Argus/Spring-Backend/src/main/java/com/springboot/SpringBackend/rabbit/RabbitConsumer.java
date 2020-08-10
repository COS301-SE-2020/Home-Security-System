package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.model.*;
import com.springboot.SpringBackend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
@Controller
public class RabbitConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);

    private final NotificationService nservice;
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
    }

    @RabbitListener(queues = {"alertQueue"})
    public void receiveAlert(RabbitAlert alert) {
        Long num = Long.valueOf(1);
        Optional<User> u =  userService.getUserById(num);

        Optional<Person> p =  personService.getPersonById(Long.valueOf(alert.getPersonId()));

        Image img = new Image(alert.getImageStr());
        imageService.createImage(img);

        if(alert.getType() == "Black") {
            if (p.get().getFname() == "Unknown") {
                nservice.createNotification(new Notification(img ,alert.getType(),
                         "Intruder: " + p.get().getFname(), u.get()));
            } else {
                nservice.createNotification(new Notification(img, alert.getType(),
                        "Intruder: " + p.get().getFname() + " " + p.get().getLname(), u.get()));
            }
        }
        else {
            nservice.createNotification(new Notification(img, alert.getType(),
                        "Suspicious person: " + p.get().getFname(), u.get()));
        }

        LOGGER.info("Notification Created");
    }

    //@RabbitListener(queues = {"featureQueue"})
    public void receivePerson(RabbitPerson psn) {
        if(psn.getType() == "Grey") {
            Image img = new Image(psn.getImageStr());
            imageService.createImage(img);

            Person p = new Person(img);
            personService.createPerson(p);

            Face f = new Face(p, psn.getFaceStr());
            faceService.createFace(f);

            LOGGER.info("Grey Person Added");
        }
        else {
            Optional<Person> p = personService.getPersonById(Long.valueOf(psn.getPersonId()));

            Face f = new Face(p.get(), psn.getFaceStr());
            faceService.createFace(f);

            LOGGER.info("Person updated");
        }
    }
}
