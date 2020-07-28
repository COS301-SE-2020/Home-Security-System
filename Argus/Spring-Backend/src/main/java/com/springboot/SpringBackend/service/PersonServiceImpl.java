package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Person;
import com.springboot.SpringBackend.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("PersonService")
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepo repo;

    @Autowired
    public PersonServiceImpl(PersonRepo psnRepo)
    {
        this.repo = psnRepo;
    }

    @Override
    public List<Person> listAllPeople() { return (List<Person>) this.repo.findAll(); }

    @Override
    public Optional<Person> getPersonById(Long id) { return this.repo.findById(id); }

    @Override
    public Person createPerson(Person x) { return this.repo.save(x); }

    @Override
    public Person updatePerson(Person x) { return this.repo.save(x); }

    @Override
    public void deletePerson(Person x) { this.repo.delete(x); }

    @Override
    public void deleteAllPeople()
    {
        this.repo.deleteAll();
    }
}

