package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserWithEmailAlreadyExists extends RuntimeException {

  private static final Logger logger = LoggerFactory.getLogger(UserWithEmailAlreadyExists.class);

  public UserWithEmailAlreadyExists(String email) {
    super("User with email " + email + " already exists");
    logger.error("Attempted to create a user with an existing email: {}", email);
  }
}