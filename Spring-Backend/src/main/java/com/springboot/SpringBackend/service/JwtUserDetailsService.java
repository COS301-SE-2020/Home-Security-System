package com.springboot.SpringBackend.service;
/*
import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
*/

//@Service
public class JwtUserDetailsService //implements UserDetailsService
{
    /*@Autowired
    UserRepo userRepo;

    // @Autowired
    // private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getUserPass(), new ArrayList<>());
    }*/
}
