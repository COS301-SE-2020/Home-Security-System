package com.springboot.SpringBackend.service;

public interface SmsSender {
    void sendSmsThreat (SmsRequest req);
    void sendSmsSuspicious (SmsRequest req);
}
