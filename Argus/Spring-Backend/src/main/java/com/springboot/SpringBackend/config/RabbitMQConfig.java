package com.springboot.SpringBackend.config;

import com.springboot.SpringBackend.rabbit.RabbitConsumer;
import org.springframework.amqp.core.*;
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
    // public static final String FANOUT_EXCHANGE = "sigma.fanout";
    public static final String ALERT_QUEUE = "alertQueue";
    public static final String NOTIFY_QUEUE = "notifyQueue";
    public static final String PERSON_QUEUE = "personQueue";
    public static final String VEHICLE_QUEUE = "vehicleQueue";
    public static final String FEATURE_QUEUE = "featureQueue";
    public static final String UPDATE_PERSON_QUEUE = "updatePersonQueue";
    public static final String UPDATE_VEHICLE_QUEUE = "updateVehicleQueue";
    // public static final String MESSAGE_QUEUE = "messageQueue";
    public static final String ALERT_KEY = "alertKey";
    public static final String NOTIFY_KEY = "notifyKey";
    public static final String PERSON_KEY = "personKey";
    public static final String VEHICLE_KEY = "vehicleKey";
    public static final String FEATURE_KEY = "featureKey";
    public static final String UPDATE_PERSON_KEY = "updatePersonKey";
    public static final String UPDATE_VEHICLE_KEY = "updateVehicleKey";

    @Bean
    Queue alertQueue()
    {
        return new Queue(ALERT_QUEUE, false);
    }

    @Bean
    Queue notifyQueue()
    {
        return new Queue(NOTIFY_QUEUE, false);
    }

    @Bean
    Queue personQueue()
    {
        return new Queue(PERSON_QUEUE, false);
    }

    @Bean
    Queue vehicleQueue()
    {
        return new Queue(VEHICLE_QUEUE, false);
    }

    @Bean
    Queue featureQueue()
    {
        return new Queue(FEATURE_QUEUE, false);
    }

    @Bean
    Queue updatePersonQueue()
    {
        return new Queue(UPDATE_PERSON_QUEUE, false);
    }

    @Bean
    Queue updateVehicleQueue()
    {
        return new Queue(UPDATE_VEHICLE_QUEUE, false);
    }

    // @Bean
    // Queue messageQueue() { return new Queue(MESSAGE_QUEUE, false); }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    // @Bean
    // FanoutExchange fanoutExchange(){ return new FanoutExchange(FANOUT_EXCHANGE); }

    @Bean
    public Binding alertBinding() {
        return BindingBuilder.bind(alertQueue()).to(directExchange()).with(ALERT_KEY);
    }

    @Bean
    public Binding notifyBinding() { return BindingBuilder.bind(notifyQueue()).to(directExchange()).with(NOTIFY_KEY); }

    @Bean
    public Binding personBinding() {
        return BindingBuilder.bind(personQueue()).to(directExchange()).with(PERSON_KEY);
    }

    @Bean
    public Binding vehicleBinding() { return BindingBuilder.bind(vehicleQueue()).to(directExchange()).with(VEHICLE_KEY); }

    @Bean
    public Binding featureBinding() { return BindingBuilder.bind(featureQueue()).to(directExchange()).with(FEATURE_KEY); }

    @Bean
    public Binding updatePersonBinding() {
        return BindingBuilder.bind(updatePersonQueue()).to(directExchange()).with(UPDATE_PERSON_KEY);
    }

    @Bean
    public Binding updateVehicleBinding() {
        return BindingBuilder.bind(updateVehicleQueue()).to(directExchange()).with(UPDATE_VEHICLE_KEY);
    }

    // @Bean
    // public Binding messageBinding() { return BindingBuilder.bind(messageQueue()).to(fanoutExchange()); }

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

    /*
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
       registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
    */
}
