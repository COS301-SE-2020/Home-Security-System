package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.config.RabbitMQConfig;
import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.model.RabbitPerson;
import com.springboot.SpringBackend.service.FaceService;
import com.springboot.SpringBackend.service.PersonService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://sigma-argus.herokuapp.com")
public class PersonController {
    private final PersonService service;
    private RabbitTemplate amqpTemplate;

    @Autowired
    public PersonController(PersonService service, RabbitTemplate template) {
        this.service = service;
        this.amqpTemplate = template;
    }

    @GetMapping("/people")
    public List<Person> getPeopleList() {
        return service.getAllPeople();
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Person x = service.getPersonById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + id));
        return ResponseEntity.ok().body(x);
    }

    @PostMapping("/people")
    public Person addPerson(@Valid @RequestBody Person x) {
        RabbitPerson p = new RabbitPerson(x.getPersonId(), "0", x.getPersonListed(), true, x.getPersonImg(), false);
        amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_PERSON_KEY, p);
        System.out.println("Person Created");
        return service.createPerson(x);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> editPerson(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody Person details) throws ResourceNotFoundException {
        Person x = service.getPersonById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + id));

        x.setPersonId(details.getPersonId());
        if(details.getPersonImg() != null) {
            x.setPersonImg(details.getPersonImg());
        }
        if(details.getFname() != null) {
            x.setFname(details.getFname());
        }
        if(details.getLname() != null)
        {
            x.setLname(details.getLname());
        }
        x.setPersonListed(details.getPersonListed());
        x.setPersonDeleted(details.getPersonDeleted());

        final Person updatedPerson = service.updatePerson(x);

        if(details.getPersonDeleted() == null) {
            RabbitPerson p = new RabbitPerson(updatedPerson.getPersonId(), "0", updatedPerson.getPersonListed(), true, updatedPerson.getPersonImg(), true);
            amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_PERSON_KEY, p);
            System.out.println("Person Updated");
        }
        else if(details.getPersonDeleted() != null) {
            RabbitPerson p = new RabbitPerson(updatedPerson.getPersonId(), "0", updatedPerson.getPersonListed(), false, updatedPerson.getPersonImg(), true);
            amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_PERSON_KEY, p);
            System.out.println("Person Deleted");
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
