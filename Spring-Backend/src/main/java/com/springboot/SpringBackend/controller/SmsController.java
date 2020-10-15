package com.springboot.SpringBackend.controller;


import com.springboot.SpringBackend.model.SmsRequest;
import com.springboot.SpringBackend.service.SmsService;
/*
import com.springboot.SpringBackend.model.MakeCall;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.net.URISyntaxException;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SmsController {

    private final SmsService service;

    @Autowired
    public SmsController(SmsService service) {
        this.service = service;
    }

    @PostMapping(value = "/sms/threat")
    public void sendSmsThreat(@Valid @RequestBody SmsRequest req){ service.sendThreat(req); }

    @PostMapping(value = "/sms/suspicious")
    public void sendSmsSuspicious(@Valid @RequestBody SmsRequest req){ service.sendSuspicious(req); }

    /*@PostMapping(value = "/voice-note", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> getVoiceNote() throws TwiMLException {
        String otp = "Police are on their way";
        Say say = new Say.Builder(otp).voice(Say.Voice.MAN).language(Say.Language.EN_US).build();
        VoiceResponse response = new VoiceResponse.Builder().say(say).build();
        return new ResponseEntity<>(response.toXml(), HttpStatus.OK);
    }*/

    /*@PostMapping(value = "/call")
    public ResponseEntity<Object> makeCall(@Valid @RequestBody MakeCall req) {
            TwilioRestClient client = new TwilioRestClient.Builder(req.getAccountId(), req.getTokenId()).build();
            PhoneNumber to = new PhoneNumber("+27840763231");
            PhoneNumber from = new PhoneNumber(req.getFrom());
            URI uri = URI.create("http://demo.twilio.com/docs/voice.xml");
            Call call = Call.creator(to, from, uri).create(client);
            return new ResponseEntity<>("Call has initiated successfully and call SID is:" + call.getSid(), HttpStatus.OK);
    }*/

    /*@PostMapping(value = "/call")
    public ResponseEntity<Object> makeCall(@Valid @RequestBody MakeCall req) throws URISyntaxException {
        Twilio.init(req.getAccountId(), req.getTokenId());

        Call call = Call.creator(new PhoneNumber(req.getTo()), new PhoneNumber(req.getFrom()),
                new URI("http://demo.twilio.com/docs/voice.xml")).create();
        return new ResponseEntity<>("Call has initiated successfully: " + call.getSid(), HttpStatus.OK);
    }*/
}
