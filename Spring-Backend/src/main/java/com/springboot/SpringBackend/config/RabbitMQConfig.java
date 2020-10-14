package com.springboot.SpringBackend.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String DIRECT_EXCHANGE = "sigma.direct";

    public static final String ALERT_QUEUE = "alertQueue";
    public static final String PERSON_QUEUE = "personQueue";
    public static final String UPDATE_PERSON_QUEUE = "updatePersonQueue";

    public static final String ALERT_KEY = "alertKey";
    public static final String PERSON_KEY = "personKey";
    public static final String UPDATE_PERSON_KEY = "updatePersonKey";

    @Bean
    public ConnectionFactory connectionFactory()
    {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("rattlesnake.rmq.cloudamqp.com");
        connectionFactory.setUri("amqps://ohskvfuw:***@rattlesnake.rmq.cloudamqp.com/ohskvfuw");
        connectionFactory.setUsername("ohskvfuw");
        connectionFactory.setPassword("HN8SBYNGPfuswoGySxiH0CyeC38v9oSP");
        connectionFactory.setVirtualHost("ohskvfuw");
        //connectionFactory.setConnectionTimeout(30000);
        return connectionFactory;
    }

    @Bean
    Queue alertQueue() {
        return new Queue(ALERT_QUEUE, false);
    }

    @Bean
    Queue personQueue() {
        return new Queue(PERSON_QUEUE, false);
    }

    @Bean
    Queue updatePersonQueue() {
        return new Queue(UPDATE_PERSON_QUEUE, false);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding alertBinding() {
        return BindingBuilder.bind(alertQueue()).to(directExchange()).with(ALERT_KEY);
    }

    @Bean
    public Binding personBinding() {

        return BindingBuilder.bind(personQueue()).to(directExchange()).with(PERSON_KEY);
    }

    @Bean
    public Binding updatePersonBinding() {
        return BindingBuilder.bind(updatePersonQueue()).to(directExchange()).with(UPDATE_PERSON_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
