package com.example.micro_recipient.controller;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class RecipientController {

  private static final Logger logger = LoggerFactory.getLogger(RecipientController.class);

  private final List<String> messages = new ArrayList<>();

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @PostConstruct
  public void init() {
    listenToQueue();
  }

  private void listenToQueue() {
    new Thread(() -> {
      while (true) {
        String message = (String) rabbitTemplate.receiveAndConvert("notificationQueue");
        if (message != null) {
          messages.add(message);
          logger.info("Received message: {}", message);
        }
        try {
          Thread.sleep(1000); // Polling every second
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    }).start();
  }

  @GetMapping
  public ResponseEntity<List<String>> getMessages() {
    logger.info("Returning messages: {}", messages);
    List<String> result = new ArrayList<>(messages);
    messages.clear();
    return ResponseEntity.ok(result);
  }
}
