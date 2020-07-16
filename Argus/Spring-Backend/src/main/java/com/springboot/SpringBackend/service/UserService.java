package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.dto.UserDTO;
import com.springboot.SpringBackend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    void createUser(User user);

    void updateUser(User user);

    //void createUserForm(UserDTO dto);

    //void updateUserForm(UserDTO dto);

    void deleteUser(User user);

    void deleteUserById(Long id);
}
