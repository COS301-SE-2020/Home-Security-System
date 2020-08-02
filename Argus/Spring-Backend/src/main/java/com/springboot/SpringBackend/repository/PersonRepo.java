package com.springboot.SpringBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.SpringBackend.model.Person;

import java.util.List;

@Repository
//public interface PersonRepo extends CrudRepository<Person, Long> { }
public interface PersonRepo extends JpaRepository<Person, Long>{
    //List<Person> findUserByName(String name);
    //List<Person> findUserBySurname(String name);
    //List<Person> findUserByFullName(String name);
}
