package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.JwtRequest;
import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.repository.UserRepo;
import com.springboot.SpringBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://sigma-argus.herokuapp.com")
public class UserController {
    private final UserService service;
    private final UserRepo repo;
    private final PasswordEncoder hash;
    private final MailerController mailer;

    @Autowired
    public UserController(UserService service, UserRepo repo, PasswordEncoder salt, MailerController email) {
        this.service = service;
        this.repo = repo;
        this.hash = salt;
        this.mailer = email;
    }

    @PostMapping("/validate")
    public ResponseEntity<JwtRequest> validatePassword(@Valid @RequestBody JwtRequest x) throws ResourceNotFoundException {
        User user = null;

        if(repo.existsUserByUsername(x.getUsername()))
        {
            user = repo.findUserByUsername(x.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + x.getUsername()));
        }
        else if(repo.existsUserByEmail(x.getUsername()))
        {
            user = repo.findUserByEmail(x.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + x.getUsername()));
        }

        if(user != null) {
            boolean isMatch = hash.matches(x.getPassword(), user.getUserPass());

            if (isMatch) {
                return ResponseEntity.ok().body(x);
            }
        }

        x.setPassword("&1&2&1234");
        return ResponseEntity.ok().body(x) ;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User x = service.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok().body(x);
    }

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User x) {
        //mailer.sendRegistration(x.getEmail());
        String passEncrypt = hash.encode(x.getUserPass());
        x.setUserPass(passEncrypt);
        return service.createUser(x);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody User details) throws ResourceNotFoundException {
        User x = service.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        if(details.getUserId() != null) {
            x.setUserId(details.getUserId());
        }
        if(!details.getProfilePhoto().equals("")) {
            x.setProfilePhoto(details.getProfilePhoto());
        }
        if(!details.getFname().equals("")) {
            x.setFname(details.getFname());
        }
        if(!details.getLname().equals("")) {
            x.setLname(details.getLname());
        }
        if(!details.getContactNo().equals("")) {
            x.setContactNo(details.getContactNo());
        }
        if(!details.getEmail().equals("")) {
            x.setEmail(details.getEmail());
        }
        if(!details.getUsername().equals("")) {
            x.setUsername(details.getUsername());
        }
        if(!details.getUserPass().equals(x.getUserPass())) {
            //x.setUserPass(details.getUserPass());
            x.setUserPass(hash.encode(details.getUserPass()));
        }
        x.setUserRole(details.getUserRole());
        x.setNotifyEmail(details.getNotifyEmail());
        x.setNotifySMS(details.getNotifySMS());
        x.setUserDeleted(details.getUserDeleted());
        if(!details.getSecureQuestion().equals(x.getSecureQuestion())) {
            x.setSecureQuestion(details.getSecureQuestion());
        }
        if((!details.getSecureAnswer().equals(x.getSecureAnswer())) && (!details.getSecureAnswer().equals(""))) {
            x.setSecureAnswer(details.getSecureAnswer());
        }
        if(details.getNetwork() != null) {
            x.setNetwork(details.getNetwork());
        }

        final User updatedUser = service.updateUser(x);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User x = service.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        service.deleteUser(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
