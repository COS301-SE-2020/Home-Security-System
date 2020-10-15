package com.springboot.SpringBackend.controller;

import com.google.common.collect.Lists;
import com.springboot.SpringBackend.config.RabbitMQConfig;
import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.model.RabbitPerson;
import com.springboot.SpringBackend.rabbit.RabbitConsumer;
import com.springboot.SpringBackend.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://sigma-argus.herokuapp.com")
public class PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    private final PersonService service;
    private final RabbitTemplate amqpTemplate;

    @Autowired
    public PersonController(PersonService service, RabbitTemplate template) {
        this.service = service;
        this.amqpTemplate = template;
    }

    @GetMapping("/people")
    public List<Person> getPeopleList() {
        return Lists.reverse(service.getAllPeople());
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Person x = service.getPersonById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + id));
        return ResponseEntity.ok().body(x);
    }

    @PostMapping("/people")
    public Person addPerson(@Valid @RequestBody Person x) {
        Person p = service.createPerson(x);
        try {
            RabbitPerson rabbitPsn = new RabbitPerson(p.getPersonId(), "0", p.getPersonListed(), true, p.getPersonImg(), false, p.getNetworkId());
            amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_PERSON_KEY, rabbitPsn);
            LOGGER.info("Person Created");
        }
        catch (NoSuchElementException ex) {
            LOGGER.info(String.valueOf(ex));
        }
        return p;
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> editPerson(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody Person details) throws ResourceNotFoundException {
        Person x = service.getPersonById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + id));


        if(details.getPersonId() != null) {
            x.setPersonId(details.getPersonId());
        }
        if(!details.getPersonImg().equals("")) {
            x.setPersonImg(details.getPersonImg());
        }
        if(!details.getFname().equals("")) {
            x.setFname(details.getFname());
        }
        if(!details.getLname().equals("")) {
            x.setLname(details.getLname());
        }
        x.setPersonListed(details.getPersonListed());
        x.setPersonDeleted(details.getPersonDeleted());
        if(details.getNetwork() != null) {
            x.setNetwork(details.getNetwork());
        }

        final Person updatedPerson = service.updatePerson(x);

        try {
            if (details.getPersonDeleted() == null) {
                RabbitPerson p = new RabbitPerson(updatedPerson.getPersonId(), "0", updatedPerson.getPersonListed(), true, updatedPerson.getPersonImg(), true, updatedPerson.getNetworkId());
                amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_PERSON_KEY, p);
                LOGGER.info("Person Updated");
            } else if (details.getPersonDeleted() != null) {
                RabbitPerson p = new RabbitPerson(updatedPerson.getPersonId(), "0", updatedPerson.getPersonListed(), false, updatedPerson.getPersonImg(), true, updatedPerson.getNetworkId());
                amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_PERSON_KEY, p);
                LOGGER.info("Person Deleted");
            }
        }
        catch (NoSuchElementException ex) {
            LOGGER.info(String.valueOf(ex));
        }

        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/people/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Person x = service.getPersonById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + id));

        service.deletePerson(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
