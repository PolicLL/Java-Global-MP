package com.example.micro_collector.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CollectorController {

  private static final Logger logger = LoggerFactory.getLogger(CollectorController.class);

  @Autowired
  private RestTemplate restTemplate;

  private static final String RECIPIENT_URL = "http://micro-recipient:8082/message";

  @Scheduled(fixedDelay = 5000)  // every M seconds
  public void collectMessages() {
    try {
      ResponseEntity<List> response = restTemplate.exchange(
          RECIPIENT_URL, HttpMethod.GET, null, List.class);
      List<String> messages = response.getBody();
      logger.info("Collected messages: {}", messages);
    } catch (Exception e) {
      logger.error("Failed to collect messages", e);
    }
  }
}
