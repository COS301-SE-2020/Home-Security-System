package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.dto.PersonDTO;
import com.springboot.SpringBackend.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> listAllPeople();

    Optional<Person> getPersonByID(Long id);

    Person createPerson(Person x);

    Person updatePerson(Person x);

    //Person createPersonForm(PersonDTO dto);

    //Person updatePersonForm(PersonDTO dto);

    void deletePerson(Person x);

    void deletePersonById(Long id);
}
