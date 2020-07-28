package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Users;
import com.springboot.SpringBackend.repository.UsersRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("UsersService")
@Transactional
public class UsersServiceImpl implements UsersService{
    private final UsersRepo repo;

    @Autowired
    public UsersServiceImpl(UsersRepo usrRepo)
    {
        this.repo = usrRepo;
    }

    @Override
    public List<Users> getAllUsers() {
        return this.repo.findAll();
    }

    @Override
    public Optional<Users> getUserById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Users createUser(Users user) { return this.repo.save(user); }

    @Override
    public Users updateUser(Users user) { return this.repo.save(user); }

    @Override
    public void deleteUser(Users user) {
        this.repo.delete(user);
    }

    @Override
    public void deleteAllUsers()
    {
        this.repo.deleteAll();
    }
}
