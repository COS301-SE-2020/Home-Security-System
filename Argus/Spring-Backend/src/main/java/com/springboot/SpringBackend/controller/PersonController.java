package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.config.RabbitMQConfig;
import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.model.RabbitPerson;
import com.springboot.SpringBackend.service.FaceService;
import com.springboot.SpringBackend.service.PersonService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    private final PersonService service;
    private final FaceService faceService;
    private AmqpTemplate amqpTemplate;

    @Autowired
    public PersonController(PersonService service, FaceService faceService, AmqpTemplate template) {
        this.service = service;
        this.faceService = faceService;
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
        RabbitPerson p = new RabbitPerson(x.getPersonId(), x.getPersonListed(), true, x.getPersonImg(), false);
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.PERSON_KEY, p);
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
        if(details.getFname() != "") {
            x.setFname(details.getFname());
        }
        if(details.getLname() != "")
        {
            x.setLname(details.getLname());
        }
        x.setPersonListed(details.getPersonListed());
        x.setPersonDeleted(details.getPersonDeleted());

        final Person updatedPerson = service.updatePerson(x);

        if(details.getPersonDeleted() != null) {
            RabbitPerson p = new RabbitPerson(x.getPersonId(), x.getPersonListed(), true, x.getPersonImg(), true);
            amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.PERSON_KEY, p);
        }
        else
        {
            RabbitPerson p = new RabbitPerson(x.getPersonId(), x.getPersonListed(), false, x.getPersonImg(), true);
            amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.PERSON_KEY, p);
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
