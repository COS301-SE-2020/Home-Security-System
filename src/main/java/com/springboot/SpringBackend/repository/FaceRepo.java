package com.springboot.SpringBackend.repository;

import com.springboot.SpringBackend.model.Face;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaceRepo extends JpaRepository<Face, Long> {
}
