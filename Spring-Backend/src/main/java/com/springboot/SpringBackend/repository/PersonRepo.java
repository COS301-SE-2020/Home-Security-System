package com.springboot.SpringBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.SpringBackend.model.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long>{ }
