package com.springboot.SpringBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.SpringBackend.model.Person;

import java.util.List;

@Repository
//public interface PersonRepo extends CrudRepository<Person, Long> { }
public interface PersonRepo extends JpaRepository<Person, Long>{ }
