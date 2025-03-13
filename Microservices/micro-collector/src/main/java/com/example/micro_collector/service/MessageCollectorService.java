package com.example.micro_collector.service;

import com.example.micro_collector.recipient.MicroRecipientClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MessageCollectorService {

  private static final Logger logger = LoggerFactory.getLogger(MessageCollectorService.class);

  private final MicroRecipientClient microRecipientClient;

  public MessageCollectorService(MicroRecipientClient microRecipientClient) {
    this.microRecipientClient = microRecipientClient;
  }

  // Runs every M seconds (M > N)
  @Scheduled(fixedRate = 10000) // Adjust M (10,000 ms = 10s)
  public void collectMessages() {
    try {
      String message = microRecipientClient.getMessage();
      logger.info("Collected message: {}", message);
    } catch (Exception e) {
      logger.error("Failed to fetch message from micro-recipient: {}", e.getMessage());
    }
  }
}
