package com.example.demo.repository.data;

import com.example.demo.dto.EventDto;
import com.example.demo.dto.TicketDto;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.Storage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;


public class StorageInitializer implements InitializingBean {

  private static final Logger logger = LoggerFactory.getLogger(StorageInitializer.class);

  private final Storage<EventDto> eventStorage;
  private final Storage<UserDto> userStorage;
  private final Storage<TicketDto> ticketStorage;

  public StorageInitializer(Storage<EventDto> eventStorage, Storage<UserDto> userStorage,
      Storage<TicketDto> ticketStorage) {
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

    System.out.println("PATH: " + dataFile);

    try {
      logger.info("Starting to load data from JSON file: {}", dataFile);
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(new File(dataFile));

      // Load events
      List<EventDto> events = objectMapper.convertValue(rootNode.get("events"),
          objectMapper.getTypeFactory().constructCollectionType(List.class, EventDto.class));
      for (EventDto event : events) {
        eventStorage.save(event, event.id());
        logger.info("Loaded Event: {}", event);
      }

      // Load users
      List<UserDto> users = objectMapper.convertValue(rootNode.get("users"),
          objectMapper.getTypeFactory().constructCollectionType(List.class, UserDto.class));
      for (UserDto user : users) {
        userStorage.save(user, user.id());
        logger.info("Loaded User: {}", user);
      }

      // Load tickets
      List<TicketDto> tickets = objectMapper.convertValue(rootNode.get("tickets"),
          objectMapper.getTypeFactory().constructCollectionType(List.class, TicketDto.class));
      for (TicketDto ticket : tickets) {
        ticketStorage.save(ticket, ticket.id());
        logger.info("Loaded Ticket: {}", ticket);
      }

      logger.info("Finished loading data successfully.");

    } catch (Exception e) {
      logger.error("Error loading storage from JSON file", e);
    }
  }
}
