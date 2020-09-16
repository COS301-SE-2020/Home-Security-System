package com.springboot.SpringBackend.service;

import com.springboot.SpringBackend.config.TwillioConfig;
import com.springboot.SpringBackend.model.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twillio")
public class TwillioSender implements SmsSender{

    private final static Logger LOGGER = LoggerFactory.getLogger(TwillioSender.class);
    private final TwillioConfig twillioConfig;
    private SmsRequest req;

    @Autowired
    public TwillioSender(TwillioConfig twillioConfig) {
        this.twillioConfig = twillioConfig;
    }

    @Override
    public void sendSmsThreat(SmsRequest req) {
        MessageCreator creator = Message.creator(
                new PhoneNumber(req.getNumb()),
                new PhoneNumber(twillioConfig.getNumber()),
                "(Argus alert) A person from your threat list has been detected"
                );
        creator.create();
        LOGGER.info("Sent sms to: " + req);
    }
    @Override
    public void sendSmsSuspicious(SmsRequest req) {
        MessageCreator creator = Message.creator(
                new PhoneNumber(req.getNumb()),
                new PhoneNumber(twillioConfig.getNumber()),
                "(Argus alert) A suspicious person has been detected"
        );
        creator.create();
        LOGGER.info("Sent sms: " + req);
    }
}
