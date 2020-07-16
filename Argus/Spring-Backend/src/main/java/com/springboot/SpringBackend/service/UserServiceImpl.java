package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.converter.UserDTOToUser;
import com.springboot.SpringBackend.dto.UserDTO;
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
    //private final UserDTOToUser dtoToUser;

    @Autowired
    public UserServiceImpl(UserRepo usrRepo)
    {
        this.repo = usrRepo;
        //this.dtoToUser = dto;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) this.repo.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public void createUser(User user) { this.repo.save(user); }

    //@Override
    //public void createUserForm(UserDTO dto) { this.createUser(dtoToUser.convert(dto)); }

    @Override
    public void updateUser(User user) { this.repo.save(user); }

    //@Override
    //public void updateUserForm(UserDTO dto) { this.updateUser(dtoToUser.convert(dto)); }

    @Override
    public void deleteUser(User user) {
        this.repo.delete(user);
    }

    @Override
    public void deleteUserById(Long id) { this.repo.deleteById(id); }

    /*
    private static UserDAO dao;

    public UserService() {
		dao = new UserDAO();
	}

    @Autowired
	public UserDAO getUserDao() {
		return dao;
	}

    @Override
    public List<User> listAllUsers() {
		List<User> list = dao.findAllUsers();
		return list;
	}

    @Override
    public User getUserById(Long id) {
		User x = dao.getUsrById(id);
		return x;
	}

    @Override
    public User createUser(User x) {
		dao.createUsr(x);
		return x;
	}

    @Override
    public User updateUser(User x) {
		dao.updateUsr(x);
		return x;
	}

    @Override
    public void deleteUser(User x) {
		dao.deleteUsr(x);
    }

    @Override
    public void deleteUserById(Long id) {
		dao.deleteUsrById(id);
	}
    */
}
