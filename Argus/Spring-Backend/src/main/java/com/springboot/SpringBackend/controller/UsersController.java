package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.Users;
import com.springboot.SpringBackend.service.UsersService;
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
public class UsersController {

    private final UsersService service;

    @Autowired
    public UsersController(UsersService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Users x = service.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok().body(x);
    }

    @PostMapping("/users")
    public Users addUser(@Valid @RequestBody Users x) {
        return service.createUser(x);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> editUser(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody Users details) throws ResourceNotFoundException {
        Users x = service.getUserById(id)
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
        final Users updatedUser = service.updateUser(x);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Users x = service.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        service.deleteUser(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
