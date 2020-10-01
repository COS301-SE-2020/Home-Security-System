package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Camera;
import com.springboot.SpringBackend.model.Notification;
import com.springboot.SpringBackend.repository.CameraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("CameraService")
@Transactional
public class CameraServiceImpl implements CameraService {
    private final CameraRepo repo;

    @Autowired
    public CameraServiceImpl(CameraRepo nRepo)
    {
        this.repo = nRepo;
    }

    @Override
    public List<Camera> getAllCameras() {
        return (List<Camera>) this.repo.findAll();
    }

    @Override
    public Optional<Camera> getCameraById(Long id) {
        return this.repo.findById(id);
    }

    @Override
    public Camera createCamera(Camera x) {
        return this.repo.save(x);
    }

    @Override
    public Camera updateCamera(Camera x) {
        return this.repo.save(x);
    }

    @Override
    public void deleteCamera(Camera x) {
        this.repo.delete(x);
    }

    @Override
    public void deleteAllCameras() {
        this.repo.deleteAll();
    }
}
