package com.example.micro_sender.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Bean
  public Queue notificationQueue() {
    return new Queue("notificationQueue", false);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange("exchange");
  }

  @Bean
  public Binding binding(Queue notificationQueue, TopicExchange exchange) {
    return BindingBuilder.bind(notificationQueue).to(exchange).with("notification.#");
  }
}
