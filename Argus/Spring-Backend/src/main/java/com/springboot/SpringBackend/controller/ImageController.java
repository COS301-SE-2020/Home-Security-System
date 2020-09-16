package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.exception.ResourceNotFoundException;
import com.springboot.SpringBackend.model.Face;
import com.springboot.SpringBackend.model.Image;
import com.springboot.SpringBackend.model.Notification;
import com.springboot.SpringBackend.service.ImageService;
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
public class ImageController {
    private final ImageService service;

    @Autowired
    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping("/images")
    public List<Image> getAllImages() {
        return service.getAllImages();
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Image x = service.getImageById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found for this id :: " + id));
        return ResponseEntity.ok().body(x);
    }

    @PostMapping("/images")
    public Image addImage(@Valid @RequestBody Image x) {
        return service.createImage(x);
    }

    @PutMapping("/images/{id}")
    public ResponseEntity<Image> editImage(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Image details) throws ResourceNotFoundException {
        Image x = service.getImageById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found for this id :: " + id));

        x.setImageId(details.getImageId());
        if(details.getPhoto() != null) {
            x.setPhoto(details.getPhoto());
        }
        x.setImageDeleted(details.getImageDeleted());
        final Image updatedImage = service.updateImage(x);
        return ResponseEntity.ok(updatedImage);
    }

    @DeleteMapping("/images/{id}")
    public Map<String, Boolean> deleteImage(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Image x = service.getImageById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found for this id :: " + id));

        service.deleteImage(x);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
