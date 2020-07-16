package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAllUsers();

    User getUsrById(Long id);

    User createUsr(User x);

    User updateUsr(User x);

    void deleteUsr(User x);

    void deleteUsrById(Long id);
}
