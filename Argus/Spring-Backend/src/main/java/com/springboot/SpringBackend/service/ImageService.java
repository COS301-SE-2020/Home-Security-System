package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    List<Image> listAllImages();

    Optional<Image> getImageById(Long id);

    void createImage(Image x);

    void updateImage(Image x);

    void deleteImage(Image x);

    void deleteImageById(Long id);
}
