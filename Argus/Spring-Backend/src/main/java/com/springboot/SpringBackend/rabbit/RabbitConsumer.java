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

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
//@Service
//@Controller
public class RabbitConsumer{
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
    public void receivedAlert(RabbitAlert alert) {
        //Session
        Long num = Long.valueOf(1);
        Optional<User> u =  userService.getUserById(num);

        Optional<Person> p =  personService.getPersonById(Long.valueOf(alert.getPersonId()));

        Image img = new Image(alert.getImageStr());
        imageService.createImage(img);

        /*try {
            Person person = new Person();
            User user= new User();

            if(p.isPresent() && u.isPresent()) {
                 person = p.get();
                 user = u.get();
            }

            if (alert.getType() == "Black") {
                nservice.createNotification(new Notification(img, alert.getType(),
                        "Intruder: " + person.getFname() + " " + person.getLname(), user));
            } else if (alert.getType() == "Grey") {
                nservice.createNotification(new Notification(img, alert.getType(),
                        "Suspicious person: " + person.getFname(), user));
            }
        }
        catch (NoSuchElementException ex) {
            LOGGER.info(String.valueOf(ex));
        }
        */

        if (alert.getType().equalsIgnoreCase("Black")) {
            nservice.createNotification(new Notification(img, alert.getType(),
                    "Intruder: " + p.get().getFname() + " " + p.get().getLname(), u.get()));
        } else {
            nservice.createNotification(new Notification(img, alert.getType(),
                    "Suspicious person: " + p.get().getFname(), u.get()));
        }

        LOGGER.info("Notification Created");
    }

    //@RabbitListener(queues = {"featureQueue"})
    /*public void receivePerson(RabbitPerson psn) {
        if(psn.getType().equalsIgnoreCase("Grey")) {
            Image img = new Image(psn.getImageStr());
            imageService.createImage(img);

            Person p = new Person(img);
            personService.createPerson(p);

            Face f = new Face(p, psn.getFaceStr());
            faceService.createFace(f);

            LOGGER.info("Grey Person Added");
        }
        else {
            try {
                Optional<Person> p = personService.getPersonById(Long.valueOf(psn.getPersonId()));
                Person person = new Person();

                if(p.isPresent()) {
                    person = p.get();
                }

                Face f = new Face(person, psn.getFaceStr());
                faceService.createFace(f);
            }
            catch (NoSuchElementException ex) {
                LOGGER.info(String.valueOf(ex));
            }

            LOGGER.info("Person updated");
        }
    }

     */
}
