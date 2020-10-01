package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraRepo extends JpaRepository<Camera, Long> { }
