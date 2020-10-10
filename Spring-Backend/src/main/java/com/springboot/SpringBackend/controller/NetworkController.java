package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.Network;
import com.springboot.SpringBackend.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "http://localhost:4200")
// @CrossOrigin(origins = "https://sigma-argus.herokuapp.com")
public class NetworkController {
    private final NetworkService service;

    @Autowired
    public NetworkController(NetworkService service) {
        this.service = service;
    }

    @GetMapping("/networks")
    public List<Network> getAllNetworks() {
        return service.getAllNetworks();
    }

    @GetMapping("/networks/{id}")
    public ResponseEntity<Network> getNetworkById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Network x = service.getNetworkById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Network not found for this id :: " + id));
        return ResponseEntity.ok().body(x);
    }

    @PostMapping("/networks")
    public Network addNetwork(@Valid @RequestBody Network x) {
        return service.createNetwork(x);
    }

    @PutMapping("/networks/{id}")
    public ResponseEntity<Network> editNetwork(@PathVariable(value = "id") Long id,
                                                         @Valid @RequestBody Network details) throws ResourceNotFoundException {
        Network x = service.getNetworkById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Network not found for this id :: " + id));

        x.setNetworkId(details.getNetworkId());
        x.setNetName(details.getNetName());
        x.setSecurityNumber(details.getSecurityNumber());

        final Network updatedNetwork = service.updateNetwork(x);
        return ResponseEntity.ok(updatedNetwork);
    }

    @DeleteMapping("/networks/{id}")
    public Map<String, Boolean> deleteNetwork(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Network x = service.getNetworkById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Network not found for this id :: " + id));

        service.deleteNetwork(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
