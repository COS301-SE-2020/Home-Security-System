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
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
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
        x.setName(details.getName());
        x.setSurname(details.getSurname());
        x.setEmail(details.getEmail());
        x.setUsername(details.getUsername());
        x.setUserPass(details.getUserPass());
        x.setUserRole(details.getUserRole());
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
