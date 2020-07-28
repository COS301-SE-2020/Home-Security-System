package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.SpringBackend.model.Users;

import java.util.List;

@Repository
//public interface UsersRepo extends CrudRepository<Users, Long> { }
public interface UsersRepo extends JpaRepository<Users, Long>{
    //List<Users> findUserByName(String name);
    //List<Users> findUserBySurname(String name);
    //List<Users> findUserByFullName(String name);
    //List<Users> findUserByRole(String role);
}
