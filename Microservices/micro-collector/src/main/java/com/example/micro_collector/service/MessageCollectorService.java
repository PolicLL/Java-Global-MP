package com.example.micro_collector.service;

import com.example.micro_collector.model.Message;
import com.example.micro_collector.recipient.MicroRecipientClient;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MessageCollectorService {

  private static final Logger logger = LoggerFactory.getLogger(MessageCollectorService.class);

  private final MicroRecipientClient microRecipientClient;

  private final MessageRepository messageRepository;

  public MessageCollectorService(MicroRecipientClient microRecipientClient,
      MessageRepository messageRepository) {
    this.microRecipientClient = microRecipientClient;
    this.messageRepository = messageRepository;
  }

  // Runs every M seconds (M > N)
  @Scheduled(fixedRate = 10000) // Adjust M (10,000 ms = 10s)
  public void collectMessages() {
    logger.info("ENTERED collectMessages()");
    try {
      List<String> message =  List.of("123"); //microRecipientClient.getMessage();
      logger.info("Input value:  {}", message);
      message.forEach(value -> messageRepository.save(new Message(value)));
      logger.info("Collected message: {}", message);
    } catch (Exception e) {
      logger.error("Failed to fetch message from micro-recipient: {}", e.getMessage());
    }
  }
}
