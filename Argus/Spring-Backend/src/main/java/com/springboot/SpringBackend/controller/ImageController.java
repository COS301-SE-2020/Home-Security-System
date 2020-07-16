package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ImageController {

    //@Autowired
    //private ImageService service;

    //@Autowired
    //private ModelMapper modelMapper;
}
