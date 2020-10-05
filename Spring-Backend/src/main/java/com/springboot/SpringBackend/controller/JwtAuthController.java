package com.springboot.SpringBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.springboot.SpringBackend.service.JwtUserDetailsService;
import com.springboot.SpringBackend.config.JwtTokenUtil;
import com.springboot.SpringBackend.model.JwtRequest;
import com.springboot.SpringBackend.model.JwtResponse;

import javax.validation.Valid;
import java.util.Objects;
*/
@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "http://localhost:4200")
// @CrossOrigin(origins = "https://sigma-argus.herokuapp.com")
public class JwtAuthController {

    @Autowired
    PasswordEncoder bcryptEncoder;

/*
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createJwtToken(@Valid @RequestBody JwtRequest authRequest) throws Exception {
        authenticateUser(authRequest.getUsername(), authRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticateUser(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }*/

    /*@PostMapping("/authenticate")
    public ResponseEntity<?> createJwtToken(@Valid @RequestBody JwtRequest authRequest) throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        boolean passwordMatch = bcryptEncoder.matches(authRequest.getPassword(), userDetails.getPassword());

        if(passwordMatch) {
            Authentication authenticate = authenticateUser(authRequest.getUsername(), authRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
        }

        return null;
    }*/

    /*private Authentication authenticateUser(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        Authentication auth = null;

        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        return auth;
    }*/
}
