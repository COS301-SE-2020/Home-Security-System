package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Face;
import com.springboot.SpringBackend.repository.FaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("FaceService")
@Transactional
public class FaceServiceImpl implements FaceService {
    private final FaceRepo repo;

    @Autowired
    public FaceServiceImpl(FaceRepo fRepo) { this.repo = fRepo; }

    @Override
    public List<Face> getAllFaces() { return this.repo.findAll(); }

    @Override
    public Optional<Face> getFaceById(Long id) { return this.repo.findById(id); }

    @Override
    public Face createFace(Face x) { return this.repo.save(x); }

    @Override
    public Face updateFace(Face x) { return this.repo.save(x); }

    @Override
    public void deleteFace(Face x) { this.repo.delete(x); }

    @Override
    public void deleteAllFaces() { this.repo.deleteAll(); }
}
