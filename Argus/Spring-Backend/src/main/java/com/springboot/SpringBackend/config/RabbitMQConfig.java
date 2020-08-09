package com.springboot.SpringBackend.config;

import com.springboot.SpringBackend.rabbit.RabbitConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {
    //@Value("${sigma.rabbitmq.exchange}")
    public static final String EXCHANGE_NAME = "sigma.direct";
    //@Value("${sigma.rabbitmq.alertQueue}")
    public static final String ALERT_QUEUE = "alertQueue";
    //@Value("${sigma.rabbitmq.personQueue}")
    public static final String PERSON_QUEUE = "personQueue";
    //@Value("${sigma.rabbitmq.alertKey}")
    public static final String ALERT_KEY = "alertKey";
    //@Value("${sigma.rabbitmq.personKey}")
    public static final String PERSON_KEY = "personKey";

    @Bean
    Queue alertQueue()
    {
        return new Queue(ALERT_QUEUE, false);
    }

    @Bean
    Queue personQueue()
    {
        return new Queue(PERSON_QUEUE, false);
    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding alertBinding() {
        return BindingBuilder.bind(alertQueue()).to(exchange()).with(ALERT_KEY);
    }

    @Bean
    public Binding perosnBinding() {
        return BindingBuilder.bind(personQueue()).to(exchange()).with(PERSON_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
