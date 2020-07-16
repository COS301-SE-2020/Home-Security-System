package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    //@Autowired
    //private NotificationService service;

    //@Autowired
    //private ModelMapper modelMapper;
}
