package com.example.micro_recipient.controller;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class RecipientController {

  private static final Logger logger = LoggerFactory.getLogger(RecipientController.class);

  private final List<String> messages = new ArrayList<>();

  @GetMapping
  public ResponseEntity<List<String>> showMessages() {
    return new ResponseEntity<>(messages, HttpStatus.OK);
  }

  @RabbitListener(queues = "notificationQueue")
  public void receiveMessage(String message) {
    messages.add(message);
    logger.info("Received message: {}", message);
  }

  public List<String> getMessages() {
    return new ArrayList<>(messages);
  }
}
