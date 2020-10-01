package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Camera;

import java.util.List;
import java.util.Optional;

public interface CameraService {
    List<Camera> getAllCameras();

    Optional<Camera> getCameraById(Long id);

    Camera createCamera(Camera x);

    Camera updateCamera(Camera x);

    void deleteCamera(Camera x);

    void deleteAllCameras();
}
