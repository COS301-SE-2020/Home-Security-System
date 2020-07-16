package com.springboot.SpringBackend.dao;

import com.springboot.SpringBackend.model.Image;

import java.util.List;

public interface ImageDAO {
    List<Image> findAllImgs();

    Image getImgById(Long id);

    Image createImg(Image x);

    Image updateImg(Image x);

    void deleteImg(Image x);

    void deleteImgById(Long id);
}
