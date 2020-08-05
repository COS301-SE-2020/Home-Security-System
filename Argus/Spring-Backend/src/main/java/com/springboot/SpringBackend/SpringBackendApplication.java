package com.springboot.SpringBackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
@ComponentScan(basePackages = {"com.*"})
@EntityScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages={"com.*"}, entityManagerFactoryRef="emf")
@PropertySource({"classpath:application.properties"})
@EnableCaching
 */
@SpringBootApplication
//@EnableScheduling
public class SpringBackendApplication {

    //public static final String EXCHANGE_NAME = "";
    //public static final String DEFAULT_PARSING_QUEUE = "";
    //public static final String ROUTING_KEY = "";

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}

	//@Bean
	//public TopicExchange notificationExchange()
    //{
    //    return new TopicExchange(EXCHANGE_NAME);
    //}

    //@Bean
    //public Queue defaultParsingQueue()
    //{
    //    return new Queue(DEFAULT_PARSING_QUEUE);
    //}

    //@Bean
    //public Binding queueToExchangeBinding() {
    //    return BindingBuilder.bind(defaultParsingQueue()).to(notificationExchange()).with(ROUTING_KEY);
    //}

    //@Bean
    //public Jackson2JsonMessageConverter messageConverter() {
    //    ObjectMapper mapper = new ObjectMapper();
    //    return new Jackson2JsonMessageConverter(mapper);
    //}

    //@Bean
    //public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
    //    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    //    rabbitTemplate.setMessageConverter(messageConverter());
    //    return rabbitTemplate;
    //}
}
