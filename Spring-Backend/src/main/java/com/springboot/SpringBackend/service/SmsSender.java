package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.model.SmsRequest;

public interface SmsSender {
    void sendSmsThreat (SmsRequest req);
    void sendSmsSuspicious (SmsRequest req);
}
