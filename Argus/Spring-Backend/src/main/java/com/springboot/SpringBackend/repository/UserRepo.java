package com.springboot.SpringBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.SpringBackend.model.User;

@Repository
//public interface UserRepo extends CrudRepository<User, Long> { }
public interface UserRepo extends JpaRepository<User, Long>{ }
