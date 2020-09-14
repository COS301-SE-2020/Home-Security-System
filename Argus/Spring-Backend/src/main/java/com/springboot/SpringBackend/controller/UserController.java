package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://sigma-argus.herokuapp.com")
public class UserController {

    private final UserService service;
    //private final PasswordEncoder hash;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
        //this.hash = salt;
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
        //String passEncrypt = hash.encode(x.getUserPass());
        //x.setUserPass(pass);
        return service.createUser(x);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody User details) throws ResourceNotFoundException {
        User x = service.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        x.setUserId(details.getUserId());
        if(details.getProfilePhoto() != null) {
            x.setProfilePhoto(details.getProfilePhoto());
        }
        if(details.getFname() != null) {
            x.setFname(details.getFname());
        }
        if(details.getLname() != null) {
            x.setLname(details.getLname());
        }
        x.setContactNo(details.getContactNo());
        x.setEmail(details.getEmail());
        x.setUsername(details.getUsername());
        x.setUserPass(details.getUserPass());
        //x.setUserPass(hash.encode(details.getUserPass()));
        x.setUserRole(details.getUserRole());
        x.setNotifyEmail(details.getNotifyEmail());
        x.setNotifySMS(details.getNotifySMS());
        x.setUserDeleted(details.getUserDeleted());
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
