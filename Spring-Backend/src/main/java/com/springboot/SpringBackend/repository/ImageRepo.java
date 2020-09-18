package com.springboot.SpringBackend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.SpringBackend.model.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long>{ }
