package com.example.demo.repository.data;

import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.repository.Storage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class StorageInitializer implements InitializingBean {

  private static final Logger logger = LoggerFactory.getLogger(StorageInitializer.class);

  private final Storage<Event> eventStorage;
  private final Storage<User> userStorage;
  private final Storage<Ticket> ticketStorage;

  public StorageInitializer(Storage<Event> eventStorage, Storage<User> userStorage,
      Storage<Ticket> ticketStorage) {
    this.eventStorage = eventStorage;
    this.userStorage = userStorage;
    this.ticketStorage = ticketStorage;
  }

  @Getter
  private String dataFile;

  public void setDataFile(String dataFile) {
    this.dataFile = dataFile;
  }

  @Override
  public void afterPropertiesSet() {
    loadStorageFromJsonFile();
  }

  private void loadStorageFromJsonFile() {
    try {
      Resource resource = new ClassPathResource(dataFile);
      InputStream inputStream = resource.getInputStream();

      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(inputStream);

      // Load events
      List<Event> events = objectMapper.convertValue(rootNode.get("events"),
          objectMapper.getTypeFactory().constructCollectionType(List.class, Event.class));
      for (Event event : events) {
        eventStorage.save(event, event.id());
        logger.info("Loaded Event: {}", event);
      }

      // Load users
      List<User> users = objectMapper.convertValue(rootNode.get("users"),
          objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
      for (User user : users) {
        userStorage.save(user, user.id());
        logger.info("Loaded User: {}", user);
      }

      // Load tickets
      List<Ticket> tickets = objectMapper.convertValue(rootNode.get("tickets"),
          objectMapper.getTypeFactory().constructCollectionType(List.class, Ticket.class));
      for (Ticket ticket : tickets) {
        ticketStorage.save(ticket, ticket.id());
        logger.info("Loaded Ticket: {}", ticket);
      }

      logger.info("Finished loading data successfully.");

    } catch (Exception e) {
      logger.error("Error loading storage from JSON file", e);
    }
  }
}
