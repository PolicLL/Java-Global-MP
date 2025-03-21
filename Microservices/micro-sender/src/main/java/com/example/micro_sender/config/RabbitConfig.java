package com.example.micro_sender.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Bean
  public Queue notificationQueue() {
    return new Queue("notificationQueue", true); // Declare a durable queue
  }

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange("notificationExchange");
  }

  @Bean
  public Binding binding() {
    return BindingBuilder.bind(notificationQueue())
        .to(directExchange())
        .with("notificationRoutingKey");
  }
}

