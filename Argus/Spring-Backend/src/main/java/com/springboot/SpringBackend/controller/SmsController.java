package com.springboot.SpringBackend.controller;

import com.springboot.SpringBackend.service.SmsRequest;
import com.springboot.SpringBackend.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sms")
public class SmsController {

    private final SmsService service;

    @Autowired
    public SmsController(SmsService service) {
        this.service = service;
    }

    /*Usage: (Post request) http://localhost:9000/sms/threat
    NB: Request body(Json data needs to be sent with it)
    {
    "phoneNumb": "enter a phone number"
    }
    */
    @PostMapping(value = "/threat")
    public void sendSmsThreat(@Valid @RequestBody SmsRequest req){
        service.sendThreat(req);
    }

    /*Usage: (Post request) http://localhost:9000/sms/suspicious
    NB: Request body(Json data needs to be sent with it)
    {
    "phoneNumb": "enter a phone number"
    }
     */
    @PostMapping(value = "/suspicious")
    public void sendSmsSuspicious(@Valid @RequestBody SmsRequest req){
        service.sendSuspicious(req);
    }
}
