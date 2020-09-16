package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Face;

import java.util.List;
import java.util.Optional;

public interface FaceService {
    List<Face> getAllFaces();

    Optional<Face> getFaceById(Long id);

    Face createFace(Face x);

    Face updateFace(Face x);

    void deleteFace(Face x);

    void deleteAllFaces();
}
