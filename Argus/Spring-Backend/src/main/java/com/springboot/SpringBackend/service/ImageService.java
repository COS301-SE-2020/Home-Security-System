package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    List<Image> listAllImages();

    Optional<Image> getImageById(Long id);

    Image createImage(Image x);

    Image updateImage(Image x);

    void deleteImage(Image x);

    void deleteImageById(Long id);
}
