package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.Face;
import com.springboot.SpringBackend.service.FaceService;
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
public class FaceController {
    private final FaceService service;

    @Autowired
    public FaceController(FaceService service) {
        this.service = service;
    }

    @GetMapping("/faces")
    public List<Face> getFaceList() {
        return service.getAllFaces();
    }

    @GetMapping("/faces/{id}")
    public ResponseEntity<Face> getFaceById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Face x = service.getFaceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Face not found for this id :: " + id));
        return ResponseEntity.ok().body(x);
    }

    @PostMapping("/faces")
    public Face addFace(@Valid @RequestBody Face x) {
        return service.createFace(x);
    }

    @PutMapping("/faces/{id}")
    public ResponseEntity<Face> editFace(@PathVariable(value = "id") Long id,
                                             @Valid @RequestBody Face details) throws ResourceNotFoundException {
        Face x = service.getFaceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Face not found for this id :: " + id));

        x.setFaceId(details.getFaceId());
        if(details.getPerson() != null) {
            x.setPerson(details.getPerson());
        }
        x.setFeatures(details.getFeatures());
        x.setFaceDeleted(details.getFaceDeleted());
        final Face updatedFace = service.updateFace(x);
        return ResponseEntity.ok(updatedFace);
    }

    @DeleteMapping("/faces/{id}")
    public Map<String, Boolean> deleteFace(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Face x = service.getFaceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Face not found for this id :: " + id));

        service.deleteFace(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
