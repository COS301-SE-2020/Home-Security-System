package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> findAllPeople();

    Person getPeopleById(Long id);

    Person createPeople(Person x);

    Person updatePeople(Person x);

    void deletePeople(Person x);

    void deletePeopleById(Long id);
}
