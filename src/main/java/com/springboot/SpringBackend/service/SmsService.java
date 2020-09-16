package com.springboot.SpringBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private final SmsSender sender;

    @Autowired
    public SmsService(@Qualifier("twillio") TwillioSender sender) {
        this.sender = sender;
    }

    public void sendThreat(SmsRequest smsRequest){
        sender.sendSmsThreat(smsRequest);
    }
    public void sendSuspicious(SmsRequest smsRequest){
        sender.sendSmsSuspicious(smsRequest);
    }
}
