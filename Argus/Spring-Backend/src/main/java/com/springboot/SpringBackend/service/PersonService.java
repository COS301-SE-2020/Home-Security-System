package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getAllPeople();

    Optional<Person> getPersonById(Long id);

    Person createPerson(Person x);

    Person updatePerson(Person x);

    void deletePerson(Person x);

    void deleteAllPeople();
}
