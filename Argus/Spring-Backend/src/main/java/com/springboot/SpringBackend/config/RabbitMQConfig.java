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
    public static final String ALERT_QUEUE = "alertQueue";
    public static final String PERSON_QUEUE = "personQueue";
    public static final String FEATURE_QUEUE = "featureQueue";
    public static final String UPDATE_QUEUE = "updateQueue";
    public static final String MESSAGE_QUEUE = "messageQueue";
    public static final String ALERT_KEY = "alertKey";
    public static final String PERSON_KEY = "personKey";
    public static final String FEATURE_KEY = "featureKey";
    public static final String UPDATE_KEY = "updateKey";
    public static final String MESSAGE_KEY = "messageKey";

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
    Queue featureQueue()
    {
        return new Queue(FEATURE_QUEUE, false);
    }

    @Bean
    Queue updateQueue()
    {
        return new Queue(UPDATE_QUEUE, false);
    }

    @Bean
    Queue messageQueue()
    {
        return new Queue(MESSAGE_QUEUE, false);
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
    public Binding personBinding() {
        return BindingBuilder.bind(personQueue()).to(exchange()).with(PERSON_KEY);
    }

    @Bean
    public Binding featureBinding() {
        return BindingBuilder.bind(featureQueue()).to(exchange()).with(FEATURE_KEY);
    }

    @Bean
    public Binding updateBinding() {
        return BindingBuilder.bind(updateQueue()).to(exchange()).with(UPDATE_KEY);
    }

    @Bean
    public Binding messageBinding() {
        return BindingBuilder.bind(messageQueue()).to(exchange()).with(MESSAGE_KEY);
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
