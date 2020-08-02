package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Image;
import com.springboot.SpringBackend.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("ImageService")
@Transactional
public class ImageServiceImpl implements ImageService{
    private final ImageRepo repo;

    @Autowired
    public ImageServiceImpl(ImageRepo imgRepo)
    {
        this.repo = imgRepo;
    }

    @Override
    public List<Image> getAllImages() { return this.repo.findAll(); }

    @Override
    public Optional<Image> getImageById(Long id) { return this.repo.findById(id); }

    @Override
    public Image createImage(Image x) { return this.repo.save(x); }

    @Override
    public Image updateImage(Image x) { return this.repo.save(x); }

    @Override
    public void deleteImage(Image x) { this.repo.delete(x); }

    @Override
    public void deleteAllImages() { this.repo.deleteAll(); }
}
