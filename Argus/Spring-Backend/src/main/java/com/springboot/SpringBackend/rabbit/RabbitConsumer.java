package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.controller.MailerController;
import com.springboot.SpringBackend.controller.SessionController;
import com.springboot.SpringBackend.model.*;
import com.springboot.SpringBackend.service.*;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.interceptor.DelegatingSmartEndpointInterceptor;

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
    private SessionController session;

    @Autowired
    public RabbitConsumer(NotificationService ns, PersonService ps,
                          UserService us, FaceService fs, ImageService is,
                          MailerController mc, SessionController sc) {
        this.nservice = ns;
        this.personService = ps;
        this.userService = us;
        this.faceService = fs;
        this.imageService = is;
        this.mailer = mc;
        this.session = sc;
    }

    @RabbitListener(queues = {"alertQueue"})
    public void receivedAlert(RabbitAlert alert) {
        // User session id
        Long id = Long.valueOf(1);
        //JSONArray arr = session.getSessionDetails();

        //for (int i = 0; i < arr.size(); i++) {
            //id = (Long) arr.get(0);
        //}

        Optional<User> u =  userService.getUserById(id);

        if(alert.getPersonId() != 0)
        {
            Optional<Person> p = personService.getPersonById(alert.getPersonId());

            // Image img = new Image(alert.getImageStr());
            // imageService.createImage(img);

            try {
                if(p.isPresent() && u.isPresent()) {
                    String email = u.get().getEmail();
                    Boolean notify = u.get().getNotifyEmail();

                    if (alert.getType().equalsIgnoreCase("Grey")) {
                        nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                            "Person: " + p.get().getFname(), u.get()));

                        if(notify) {
                            mailer.sendWithAttatchGL(email);
                        }
                    } else {
                        nservice.createNotification(new Notification(alert.getImageStr(), "Threat",
                            "Intruder: " + p.get().getFname() + " " + p.get().getLname(), u.get()));

                        if(notify) {
                            // mailer.sendWithAttatchBL(email);
                        }
                    }
                }
            }
            catch (NoSuchElementException ex) {
                LOGGER.info(String.valueOf(ex));
            }
        }
        else
        {
            try {
                if(u.isPresent()) {
                    String email = u.get().getEmail();
                    Boolean notify = u.get().getNotifyEmail();

                    if (alert.getType().equalsIgnoreCase("Grey")) {
                        nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                                "Person: " + "Unknown", u.get()));

                        if (notify) {
                            // mailer.sendWithAttatchGL(email);
                        }
                    }
                }
            }
            catch (NoSuchElementException ex) {
                LOGGER.info(String.valueOf(ex));
            }
        }

        LOGGER.info("Notification Created");
    }

    @RabbitListener(queues = {"personQueue"})
    public void receivePerson(RabbitPerson psn) {
        // Creating a Grey-list person from Python
        if(psn.getPersonId() == 0) {
            // Image img = new Image(psn.getImageStr());
            // imageService.createImage(img);

            Person p = new Person(psn.getImageStr());
            personService.createPerson(p);

            LOGGER.info("Grey-list Person Added");
        }
    }

    @RabbitListener(queues = {"featureQueue"})
    public void receiveFeature(RabbitFeature feature)
    {
        // Creating features for a new (white/black) listed person from Angular
        try {
            Optional<Person> p = personService.getPersonById(feature.getPersonId());

            if(p.isPresent()) {
                Face f = new Face(p.get(), feature.getFaceStr());
                faceService.createFace(f);
            }
        }
        catch (NoSuchElementException ex) {
            LOGGER.info(String.valueOf(ex));
        }

        LOGGER.info("Person updated, added face features");
    }
}
