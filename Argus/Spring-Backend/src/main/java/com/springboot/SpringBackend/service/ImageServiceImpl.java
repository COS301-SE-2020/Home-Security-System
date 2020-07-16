package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.dao.ImageDAO;
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
    public List<Image> listAllImages() { return (List<Image>) this.repo.findAll(); }

    @Override
    public Optional<Image> getImageById(Long id) { return this.repo.findById(id); }

    @Override
    public void createImage(Image x) { this.repo.save(x); }

    @Override
    public void updateImage(Image x) { this.repo.save(x); }

    @Override
    public void deleteImage(Image x) { this.repo.delete(x); }

    @Override
    public void deleteImageById(Long id) { this.repo.deleteById(id); }

	/*
    @Autowired
    private static ImageDAO dao;

    @Autowired
    public ImageService() {
		dao = new ImageDAO();
	}

	public ImageDAO getImageDao() {
		return dao;
	}

    @Override
    public List<Image> listAllImages() {
		List<Image> list = dao.findAllImgs();
		return list;
	}

    @Override
    public Image getImageById(Long id) {
		Image x = dao.getImgById(id);
		return x;
	}

    @Override
    public Image createImage(Image x) {
		dao.createImg(x);
		return x;
	}

    @Override
    public Image updateImage(Image x) {
		dao.updateImg(x);
		return x;
	}

    @Override
    public void deleteImage(Image x) {
		dao.deleteImg(x);
    }

    @Override
    public void deleteImageById(Long id) {
		dao.deleteImgById(id);
	}
	*/
}
