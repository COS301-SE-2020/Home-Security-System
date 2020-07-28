package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<Users> getAllUsers();

    Optional<Users> getUserById(Long id);

    Users createUser(Users user);

    Users updateUser(Users user);

    void deleteUser(Users user);

    void deleteAllUsers();
}
