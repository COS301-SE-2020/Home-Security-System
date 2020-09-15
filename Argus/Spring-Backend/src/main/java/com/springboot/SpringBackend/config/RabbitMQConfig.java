package com.springboot.SpringBackend.config;

import com.springboot.SpringBackend.rabbit.RabbitConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {
    public static final String DIRECT_EXCHANGE = "sigma.direct";

    public static final String ALERT_QUEUE = "alertQueue";
    public static final String NOTIFY_QUEUE = "notifyQueue";
    public static final String PERSON_QUEUE = "personQueue";
    public static final String VEHICLE_QUEUE = "vehicleQueue";
    public static final String FEATURE_QUEUE = "featureQueue";
    public static final String UPDATE_PERSON_QUEUE = "updatePersonQueue";
    public static final String UPDATE_VEHICLE_QUEUE = "updateVehicleQueue";

    public static final String ALERT_KEY = "alertKey";
    public static final String NOTIFY_KEY = "notifyKey";
    public static final String PERSON_KEY = "personKey";
    public static final String VEHICLE_KEY = "vehicleKey";
    public static final String FEATURE_KEY = "featureKey";
    public static final String UPDATE_PERSON_KEY = "updatePersonKey";
    public static final String UPDATE_VEHICLE_KEY = "updateVehicleKey";

   /* CachingConnectionFactory connectionFactory=new CachingConnectionFactory("bunny.cloudamqp.com");
    connectionFactory.("rozcdysg");
    connectionFactory.setPassword("Mx9GntDW4WBJvmY2_M_Qr2_a4gRGc3_G");
    connectionFactory.setVirtualHost("rozcdysg");*/

    @Bean
    Queue alertQueue() {
        return new Queue(ALERT_QUEUE, true, false, true);
    }

    @Bean
    Queue notifyQueue() {
        return new Queue(NOTIFY_QUEUE, true, false, true);
    }

    @Bean
    Queue personQueue() {
        return new Queue(PERSON_QUEUE, true, false, true);
    }

    @Bean
    Queue vehicleQueue()
    {
        return new Queue(VEHICLE_QUEUE, true, false, true);
    }

    @Bean
    Queue featureQueue() {
        return new Queue(FEATURE_QUEUE, true, false, true);
    }

    @Bean
    Queue updatePersonQueue() {
        return new Queue(UPDATE_PERSON_QUEUE, true, false, true);
    }

    @Bean
    Queue updateVehicleQueue() {
        return new Queue(UPDATE_VEHICLE_QUEUE, true, false, true);
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
    public Binding notifyBinding() {
        return BindingBuilder.bind(notifyQueue()).to(directExchange()).with(NOTIFY_KEY); }

    @Bean
    public Binding personBinding() {

        return BindingBuilder.bind(personQueue()).to(directExchange()).with(PERSON_KEY);
    }

    @Bean
    public Binding vehicleBinding() {
        return BindingBuilder.bind(vehicleQueue()).to(directExchange()).with(VEHICLE_KEY); }

    @Bean
    public Binding featureBinding() {
        return BindingBuilder.bind(featureQueue()).to(directExchange()).with(FEATURE_KEY); }

    @Bean
    public Binding updatePersonBinding() {
        return BindingBuilder.bind(updatePersonQueue()).to(directExchange()).with(UPDATE_PERSON_KEY);
    }

    @Bean
    public Binding updateVehicleBinding() {
        return BindingBuilder.bind(updateVehicleQueue()).to(directExchange()).with(UPDATE_VEHICLE_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
