package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    void deleteAllUsers();
}
