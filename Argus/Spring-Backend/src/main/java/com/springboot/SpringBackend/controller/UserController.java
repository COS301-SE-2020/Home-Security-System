package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.dto.UserDTO;
import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.User;
import com.springboot.SpringBackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;
    private final ModelMapper modelMapper;
    //private final UserToUserDTO userToDTO;

    @Autowired
    public UserController(UserService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
        //this.personToDTO = personToDTO;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllPersons() {
        List<User> list = service.getAllUsers();
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getProfileById(@PathVariable(value = "id") Long id) {
        User x = service.getUserById(id).get();
        return ResponseEntity.ok().body(convertToDto(x));
    }

    @PostMapping("/users")
    public User makeUser(@Valid @RequestBody UserDTO dto) {
        return service.createUser(convertToEntity(dto));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable(value = "id") Long pid,
                                             @Valid @RequestBody User personDetails) throws ResourceNotFoundException {
        User x = service.getUserById(pid)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + pid));

        x.setUserId(personDetails.getUserId());
        x.setName(personDetails.getName());
        x.setSurname(personDetails.getSurname());
        x.setEmail(personDetails.getEmail());
        x.setUsername(personDetails.getUsername());
        x.setPassword(personDetails.getPassword());
        x.setRole(personDetails.getRole());
        x.setProfilePhoto(personDetails.getProfilePhoto());
        final User updatedPerson = service.updateUser(personDetails);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long pid) throws ResourceNotFoundException {
        User x = service.getUserById(pid)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + pid));

        service.deleteUser(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public UserDTO convertToDto(User x) {
        UserDTO dto = modelMapper.map(x, UserDTO.class);
        return dto;
    }

    public User convertToEntity(UserDTO dto) {
        User x = modelMapper.map(dto, User.class);
        return x;
    }
}
