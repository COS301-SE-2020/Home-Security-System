package com.springboot.SpringBackend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.springboot.SpringBackend.model.RabbitMessage;

@Controller
public class MessageController {
    @MessageMapping("/message")
    @SendTo("/notification/messages")
    public RabbitMessage sendMessage(RabbitMessage message) throws Exception {
        Thread.sleep(1000);
        return new RabbitMessage(HtmlUtils.htmlEscape(message.getMessage()));
    }
}
