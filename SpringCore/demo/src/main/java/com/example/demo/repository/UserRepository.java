package com.example.demo.repository;

import com.example.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepository extends GenericRepository<User> {

  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

  public UserRepository(Storage<User> storage) {
    super(storage);
    logger.info("UserRepository created with storage: {}", storage);
  }

  public boolean doesUserByEmailExists(String email) {
    logger.debug("Checking if user exists with email: {}", email);
    boolean exists = storage.getStorage().values().stream().anyMatch(user -> user.email().equals(email));
    logger.info("User with email: {} exists: {}", email, exists);
    return exists;
  }

  public boolean doesUserByIdExists(String id) {
    logger.debug("Checking if user exists with ID: {}", id);
    boolean exists = storage.getStorage().values().stream().anyMatch(user -> user.id().equals(id));
    logger.info("User with ID: {} exists: {}", id, exists);
    return exists;
  }
}
