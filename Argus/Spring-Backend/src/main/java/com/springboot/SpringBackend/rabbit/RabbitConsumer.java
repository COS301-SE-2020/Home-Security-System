package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.config.RabbitMQConfig;
import com.springboot.SpringBackend.controller.MailerController;
import com.springboot.SpringBackend.controller.SessionController;
import com.springboot.SpringBackend.model.*;
import com.springboot.SpringBackend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class RabbitConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);
    private final NotificationService nservice;
    private final PersonService personService;
    private final UserService userService;
    private final FaceService faceService;
    private final ImageService imageService;
    private MailerController mailer;
    private SessionController session;
    private AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitConsumer(NotificationService ns, PersonService ps,
                          UserService us, FaceService fs, ImageService is,
                          MailerController mc, SessionController sc, AmqpTemplate template) {
        this.nservice = ns;
        this.personService = ps;
        this.userService = us;
        this.faceService = fs;
        this.imageService = is;
        this.mailer = mc;
        this.session = sc;
        this.amqpTemplate = template;
    }

    @RabbitListener(queues = {"alertQueue"})
    public void receivedAlert(RabbitAlert alert) {
        // User session id
        Long id = Long.valueOf(1);
        /*mailer.setImagePath("C:\\Users\\Brad\\Home-Security-System\\Argus\\Angular-Frontend\\src\\assets\\Images\\Argus.png");
        JSONArray arr = session.getSessionDetails();

        for (int i = 0; i < arr.size(); i++) {
            id = (Long) arr.get(0);
        }
        */

        Optional<User> u =  userService.getUserById(id);

        if(alert.getPersonId() != 0)
        {
            Optional<Person> p = personService.getPersonById(alert.getPersonId());

            // Image img = new Image(alert.getImageStr());
            // imageService.createImage(img);

            try {
                if(p.isPresent() && u.isPresent()) {
                    String email = u.get().getEmail();
                    Boolean notify1 = u.get().getNotifyEmail();
                    Boolean notify2 = u.get().getNotifySMS();

                    if (alert.getType().equalsIgnoreCase("Grey")) {
                        nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                            "Person: " + p.get().getFname(), u.get()));
                    } else {
                        nservice.createNotification(new Notification(alert.getImageStr(), "Threat",
                            "Intruder: " + p.get().getFname() + " " + p.get().getLname(), u.get()));

                        if(notify1) {
                            mailer.sendWithAttatchBL(email);
                        }
                        if(notify2) {
                            //send SMS
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
                    if (alert.getType().equalsIgnoreCase("Grey")) {
                        nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                                "Person: " + "Unknown", u.get()));
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

            RabbitPerson updatePerson = new RabbitPerson(p.getPersonId(), psn.getTempId(),psn.getType(), true, psn.getImageStr(), true);
            amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_KEY, updatePerson);

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

        LOGGER.info("Person updated, added facial features");
    }
}
