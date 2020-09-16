package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepo repo;

    @Autowired
    public UserServiceImpl(UserRepo usrRepo)
    {
        this.repo = usrRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return this.repo.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public User createUser(User user) { return this.repo.save(user); }

    @Override
    public User updateUser(User user) { return this.repo.save(user); }

    @Override
    public void deleteUser(User user) {
        this.repo.delete(user);
    }

    @Override
    public void deleteAllUsers()
    {
        this.repo.deleteAll();
    }
}
