package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.config.RabbitMQConfig;
import com.springboot.SpringBackend.controller.MailerController;
import com.springboot.SpringBackend.controller.SessionController;
import com.springboot.SpringBackend.model.*;
import com.springboot.SpringBackend.service.*;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class RabbitConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);
    private final NotificationService nservice;
    private final PersonService personService;
    private final VehicleService vehicleService;
    private final UserService userService;
    private final FaceService faceService;
    private final ImageService imageService;
    private SmsSender sender;
    private MailerController mailer;
    private SessionController session;
    private RabbitTemplate amqpTemplate;

    @Autowired
    public RabbitConsumer(NotificationService ns, PersonService ps, VehicleService vs,
                          UserService us, FaceService fs, ImageService is, SmsSender sms,
                          MailerController mc, SessionController sc, RabbitTemplate template) {
        this.nservice = ns;
        this.personService = ps;
        this.vehicleService = vs;
        this.userService = us;
        this.faceService = fs;
        this.imageService = is;
        this.sender = sms;
        this.mailer = mc;
        this.session = sc;
        this.amqpTemplate = template;
    }

    @RabbitListener(queues = {"alertQueue"})
    public void receivedAlert(RabbitAlert alert) {

        // User session id
        Long id = Long.valueOf(1);
        //JSONArray arr = session.getSessionDetails();

        //for (int i = 0; i < arr.size(); i++) {
            //id = (Long) arr.get(0);
            Optional<User> u = userService.getUserById(id);

            if (alert.getPersonId() != 0) {
                Optional<Person> p = personService.getPersonById(alert.getPersonId());

                // Image img = new Image(alert.getImageStr());
                // imageService.createImage(img);

                try {
                    if (p.isPresent() && u.isPresent()) {
                        String email = u.get().getEmail();
                        Boolean notify1 = u.get().getNotifyEmail();
                        Boolean notify2 = u.get().getNotifySMS();

                        if (p.get() == null) {
                            // Recreate the person
                            Person psn = new Person(alert.getPersonId(), alert.getImageStr());
                            personService.createPerson(psn);
                            // Update them to the correct list
                            RabbitPerson updatePerson = new RabbitPerson(psn.getPersonId(), "0", psn.getPersonListed(), true, alert.getImageStr(), true);
                            amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_PERSON_KEY, updatePerson);
                            // Send notification
                            nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                                    "Person: " + p.get().getFname(), u.get()));
                        } else if (p.get().getPersonDeleted() != null) {

                            p.get().setPersonDeleted(null);
                            personService.updatePerson(p.get());

                            if (alert.getType().equalsIgnoreCase("Grey")) {
                                nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                                        "Person: " + p.get().getFname(), u.get()));
                            } else {
                                nservice.createNotification(new Notification(alert.getImageStr(), "Threat",
                                        "Intruder: " + p.get().getFname() + " " + p.get().getLname(), u.get()));

                                if (notify1) {
                                    mailer.sendWithAttatchBL(email);
                                }
                                if (notify2) {
                                    //send SMS
                                }
                            }
                        } else {
                            if (alert.getType().equalsIgnoreCase("Grey")) {
                                nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                                        "Person: " + p.get().getFname(), u.get()));
                            } else {
                                nservice.createNotification(new Notification(alert.getImageStr(), "Threat",
                                        "Intruder: " + p.get().getFname() + " " + p.get().getLname(), u.get()));

                                if (notify1) {
                                    mailer.sendWithAttatchBL(email);
                                }
                                if (notify2) {
                                    SmsRequest request = new SmsRequest(u.get().getContactNo());
                                    sender.sendSmsThreat(request);
                                }
                            }
                        }
                    }

                } catch (NoSuchElementException ex) {
                    LOGGER.info(String.valueOf(ex));
                }
            } else {
                try {
                    if (u.isPresent()) {
                        if (alert.getType().equalsIgnoreCase("Grey")) {
                            nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                                    "Person: " + "Unknown", u.get()));
                        }
                    }
                } catch (NoSuchElementException ex) {
                    LOGGER.info(String.valueOf(ex));
                }
            }

            LOGGER.info("Notification Created");
        //}
    }

    @RabbitListener(queues = {"notifyQueue"})
    public void receivedNotification(RabbitAlert alert) {
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
            amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_PERSON_KEY, updatePerson);

            LOGGER.info("Grey-list Person Added");
        }
    }

    @RabbitListener(queues = {"vehicleQueue"})
    public void receiveVehicle(RabbitVehicle motor) {
        // Creating a Grey-list vehicles from Python
        if(motor.getVehicleId() == 0) {
            // Image img = new Image(motor.getImageStr());
            // imageService.createImage(img);

            Vehicle v = new Vehicle(motor.getImageStr());
            vehicleService.createVehicle(v);

            RabbitVehicle updateVehicle = new RabbitVehicle(v.getPersonId(), motor.getTempId(),motor.getType(), true, motor.getImageStr(), true);
            amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_VEHICLE_KEY, updateVehicle);

            LOGGER.info("Grey-list Vehicle Added");
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

        LOGGER.info("Added facial features");
    }
}
