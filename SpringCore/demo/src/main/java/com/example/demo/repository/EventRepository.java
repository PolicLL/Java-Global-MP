package com.example.demo.repository;

import com.example.demo.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventRepository extends GenericRepository<Event> {

  private static final Logger logger = LoggerFactory.getLogger(EventRepository.class);

  public EventRepository(Storage<Event> storage) {
    super(storage);
    logger.info("EventRepository created with storage: {}", storage);
  }

  public boolean doesEventExistsById(String id) {
    logger.debug("Checking if event exists with ID: {}", id);
    boolean exists = storage.getStorage().values().stream().anyMatch(event -> event.id().equals(id));
    logger.info("Event with ID: {} exists: {}", id, exists);
    return exists;
  }
}
