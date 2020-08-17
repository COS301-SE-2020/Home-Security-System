package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.controller.MailerController;
import com.springboot.SpringBackend.model.*;
import com.springboot.SpringBackend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class RabbitConsumer{
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);

    private final NotificationService nservice;
    private final PersonService personService;
    private final UserService userService;
    private final FaceService faceService;
    private final ImageService imageService;
    private MailerController mailer;

    @Autowired
    public RabbitConsumer(NotificationService ns, PersonService ps,
                          UserService us, FaceService fs, ImageService is, MailerController mc) {
        this.nservice = ns;
        this.personService = ps;
        this.userService = us;
        this.faceService = fs;
        this.imageService = is;
        this.mailer = mc;
    }

    @RabbitListener(queues = {"alertQueue"})
    public void receivedAlert(RabbitAlert alert) {
        // User session id
        Long num = Long.valueOf(1);
        Optional<User> u =  userService.getUserById(num);

        Optional<Person> p =  personService.getPersonById(alert.getPersonId());

        // Image img = new Image(alert.getImageStr());
        // imageService.createImage(img);

        try {
            if(p.isPresent() && u.isPresent()) {
                String email = u.get().getEmail();

                if (alert.getType().equalsIgnoreCase("Grey")) {
                    nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                        "Person: " + p.get().getFname(), u.get()));

                    mailer.sendWithAttatchGL(email);

                } else {
                    nservice.createNotification(new Notification(alert.getImageStr(), "Threat",
                        "Intruder: " + p.get().getFname() + " " + p.get().getLname(), u.get()));

                    mailer.sendWithAttatchBL(email);
                }
            }
        }
        catch (NoSuchElementException ex) {
            LOGGER.info(String.valueOf(ex));
        }

        LOGGER.info("Notification Created");
    }

    @RabbitListener(queues = {"featureQueue"})
    public void receivePerson(RabbitPerson psn) {
        // Creating a Grey-list person from Python
        if(psn.getType().equalsIgnoreCase("Grey")) {
            // Image img = new Image(psn.getImageStr());
            // imageService.createImage(img);

            Person p = new Person(psn.getImageStr());
            personService.createPerson(p);

            Face f = new Face(p, psn.getFaceStr());
            faceService.createFace(f);

            LOGGER.info("Grey-list Person Added");
        }
        // Creating features for a new (white/black) listed person from Angular
        else {
            try {
                Optional<Person> p = personService.getPersonById(psn.getPersonId());

                if(p.isPresent()) {
                    Face f = new Face(p.get(), psn.getFaceStr());
                    faceService.createFace(f);
                }
            }
            catch (NoSuchElementException ex) {
                LOGGER.info(String.valueOf(ex));
            }

            LOGGER.info("Person updated, added face features");
        }
    }
}
