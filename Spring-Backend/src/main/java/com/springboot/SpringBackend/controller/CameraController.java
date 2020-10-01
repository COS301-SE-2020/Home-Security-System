package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.Camera;
import com.springboot.SpringBackend.service.CameraService;
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
public class CameraController {
    private final CameraService service;

    @Autowired
    public CameraController(CameraService service) {
        this.service = service;
    }

    @GetMapping("/Cameras")
    public List<Camera> getAllCameras() {
        return service.getAllCameras();
    }

    @GetMapping("/Cameras/{id}")
    public ResponseEntity<Camera> getCameraById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Camera x = service.getCameraById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Camera not found for this id :: " + id));
        return ResponseEntity.ok().body(x);
    }

    @PostMapping("/Cameras")
    public Camera addCamera(@Valid @RequestBody Camera x) {
        return service.createCamera(x);
    }

    @PutMapping("/Cameras/{id}")
    public ResponseEntity<Camera> editCamera(@PathVariable(value = "id") Long id,
                                               @Valid @RequestBody Camera details) throws ResourceNotFoundException {
        Camera x = service.getCameraById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Camera not found for this id :: " + id));

        x.setCameraId(details.getCameraId());
        x.setServerURL(details.getServerURL());

        final Camera updatedCamera = service.updateCamera(x);
        return ResponseEntity.ok(updatedCamera);
    }

    @DeleteMapping("/Cameras/{id}")
    public Map<String, Boolean> deleteCamera(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Camera x = service.getCameraById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Camera not found for this id :: " + id));

        service.deleteCamera(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
