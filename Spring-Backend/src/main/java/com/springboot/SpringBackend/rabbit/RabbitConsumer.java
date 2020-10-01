package com.springboot.SpringBackend.rabbit;

import com.springboot.SpringBackend.config.RabbitMQConfig;
import com.springboot.SpringBackend.controller.MailerController;
import com.springboot.SpringBackend.model.*;
import com.springboot.SpringBackend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class RabbitConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);
    private final NotificationService nservice;
    private final PersonService personService;
    private final VehicleService vehicleService;
    private final UserService userService;
    private final SmsSender sender;
    private final MailerController mailer;
    private final RabbitTemplate amqpTemplate;

    @Autowired
    public RabbitConsumer(NotificationService ns, PersonService ps, VehicleService vs,
                          UserService us, SmsSender sms,
                          MailerController mc, RabbitTemplate template) {
        this.nservice = ns;
        this.personService = ps;
        this.vehicleService = vs;
        this.userService = us;
        this.sender = sms;
        this.mailer = mc;
        this.amqpTemplate = template;
    }

    @RabbitListener(queues = {"alertQueue"})
    public void receivedAlert(RabbitAlert alert) {
        List<User> arr = userService.getAllUsers();

        for (User u : arr) {
            //if(u.getNetwork() == alert.getNetwork())
            //{
            if (alert.getPersonId() != 0) {
                Optional<Person> p = personService.getPersonById(alert.getPersonId());

                try {
                    if (p.isPresent()) {
                        String email = u.getEmail();
                        Boolean notify1 = u.getNotifyEmail();
                        Boolean notify2 = u.getNotifySMS();

                        if (p.get().getPersonDeleted() != null) {
                            if (alert.getType().equalsIgnoreCase("Grey")) {
                                nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                                        "Person: " + p.get().getFname(), u));
                            } else {
                                nservice.createNotification(new Notification(alert.getImageStr(), "Threat",
                                        "Intruder: " + p.get().getFname() + " " + p.get().getLname(), u));

                                if (notify1) {
                                    mailer.sendmailBlack(email);
                                }
                                if (notify2) {
                                    SmsRequest request = new SmsRequest(u.getContactNo());
                                    sender.sendSmsThreat(request);
                                }
                            }
                        } else {
                            p.get().setPersonDeleted(null);
                            personService.updatePerson(p.get());

                            if (alert.getType().equalsIgnoreCase("Grey")) {
                                nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                                        "Person: " + p.get().getFname(), u));
                            } else {
                                nservice.createNotification(new Notification(alert.getImageStr(), "Threat",
                                        "Intruder: " + p.get().getFname() + " " + p.get().getLname(), u));

                                if (notify1) {
                                    mailer.sendmailBlack(email);
                                }
                                if (notify2) {
                                    SmsRequest request = new SmsRequest(u.getContactNo());
                                    sender.sendSmsThreat(request);
                                }
                            }
                        }
                    }
                            /*else if (p.get() == null) {
                                // Recreate the person
                                Person psn = new Person(alert.getPersonId(), alert.getImageStr());
                                personService.createPerson(psn);
                                // Update them to the correct list
                                RabbitPerson updatePerson = new RabbitPerson(psn.getPersonId(), "0", psn.getPersonListed(), true, alert.getImageStr(), true);
                                amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_PERSON_KEY, updatePerson);
                                // Send notification
                                nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                                        "Person: " + p.get().getFname(), u));
                            }*/

                } catch (NoSuchElementException ex) {
                    LOGGER.info(String.valueOf(ex));
                }
            } else {
                try {
                    if (alert.getType().equalsIgnoreCase("Grey")) {
                        nservice.createNotification(new Notification(alert.getImageStr(), "Suspicious",
                                "Person: " + "Unknown", u));
                    }
                } catch (NoSuchElementException ex) {
                    LOGGER.info(String.valueOf(ex));
                }
            }

            LOGGER.info("Notification Created");
        }
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
            Vehicle v = new Vehicle(motor.getImageStr());
            vehicleService.createVehicle(v);

            RabbitVehicle updateVehicle = new RabbitVehicle(v.getPersonId(), motor.getTempId(),motor.getType(), true, motor.getImageStr(), true);
            amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_VEHICLE_KEY, updateVehicle);

            LOGGER.info("Grey-list Vehicle Added");
        }
    }
}
