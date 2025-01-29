package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventNotFoundException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(EventNotFoundException.class);

  public EventNotFoundException(String eventId) {
    super("Event with ID " + eventId + " does not exist");
    logger.error("Attempted to access non-existing event with ID: {}", eventId);
  }
}
