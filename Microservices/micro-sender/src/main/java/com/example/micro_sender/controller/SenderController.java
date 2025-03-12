package com.example.micro_sender.controller;

import com.example.micro_sender.model.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class SenderController {

  private static final Logger logger = LoggerFactory.getLogger(SenderController.class);

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @PostMapping
  public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
    logger.info("Received notification: {}", request);
    rabbitTemplate.convertAndSend("notificationQueue", request.getMessage());
    return ResponseEntity.ok("Message sent to the queue");
  }
}
