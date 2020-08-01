package com.springboot.SpringBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.SpringBackend.model.User;

import java.util.List;

@Repository
//public interface UserRepo extends CrudRepository<Users, Long> { }
public interface UserRepo extends JpaRepository<User, Long>{
    //List<Users> findUserByName(String name);
    //List<Users> findUserBySurname(String name);
    //List<Users> findUserByFullName(String name);
    //List<Users> findUserByRole(String role);
}
