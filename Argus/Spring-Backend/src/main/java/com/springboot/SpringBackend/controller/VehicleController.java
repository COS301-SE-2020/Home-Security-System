package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.config.RabbitMQConfig;
import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.RabbitPerson;
import com.springboot.SpringBackend.model.RabbitVehicle;
import com.springboot.SpringBackend.model.Vehicle;
import com.springboot.SpringBackend.service.VehicleService;
import org.springframework.amqp.core.AmqpTemplate;
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
public class VehicleController {
    private final VehicleService service;
    private AmqpTemplate amqpTemplate;

    @Autowired
    public VehicleController(VehicleService service, AmqpTemplate template) {
        this.service = service;
        this.amqpTemplate = template;
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicle() {
        return service.getAllVehicles();
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Vehicle x = service.getVehicleById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + id));
        return ResponseEntity.ok().body(x);
    }

    @PostMapping("/vehicles")
    public Vehicle addVehicle(@Valid @RequestBody Vehicle x) {
        RabbitVehicle v = new RabbitVehicle(x.getPersonId(), "0", x.getVehicleListed(), true, x.getVehicleImg(), false);
        amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_VEHICLE_KEY, v);
        System.out.println("Vehicle Created");
        return service.createVehicle(x);
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> editVehicle(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody Vehicle details) throws ResourceNotFoundException {
        Vehicle x = service.getVehicleById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + id));

        x.setVehicleId(details.getVehicleId());
        if(details.getVehicleImg() != null) {
            x.setVehicleImg(details.getVehicleImg());
        }
        x.setVehicleListed(details.getVehicleListed());
        x.setVehicleMake(details.getVehicleMake());
        x.setVehicleModel(details.getVehicleModel());
        x.setVehicleColour(details.getVehicleColour());
        x.setLicenseNo(details.getLicenseNo());
        x.setVehicleDeleted(details.getVehicleDeleted());
        final Vehicle updatedVehicle = service.updateVehicle(x);

        if(details.getVehicleDeleted() == null) {
            RabbitVehicle v = new RabbitVehicle(updatedVehicle.getVehicleId(), "0", updatedVehicle.getVehicleListed(), true, updatedVehicle.getVehicleImg(), true);
            amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_VEHICLE_KEY, v);
            System.out.println("Vehicle Updated");
        }
        else if(details.getVehicleDeleted() != null) {
            RabbitVehicle v = new RabbitVehicle(updatedVehicle.getPersonId(), "0", updatedVehicle.getVehicleListed(), false, updatedVehicle.getVehicleImg(), true);
            amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.UPDATE_VEHICLE_KEY, v);
            System.out.println("Vehicle Deleted");
        }

        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/vehicle/{id}")
    public Map<String, Boolean> deleteVehicle(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Vehicle x = service.getVehicleById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + id));

        service.deleteVehicle(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
