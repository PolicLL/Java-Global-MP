package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundException extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(UserNotFoundException.class);

  public UserNotFoundException(String userId) {
    super("User with ID " + userId + " does not exist");
    logger.error("Attempted to access non-existing user with ID: {}", userId);
  }
}
