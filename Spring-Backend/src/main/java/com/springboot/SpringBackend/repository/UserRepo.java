package com.springboot.SpringBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.SpringBackend.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    Boolean existsUserByUsername(String Username);
    Boolean existsUserByEmail(String email);
}
