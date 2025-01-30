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
    return storage.getStorage().values().stream().anyMatch(user -> user.email().equals(email));
  }

  public boolean doesUserByIdExists(String id) {
    return storage.getStorage().values().stream().anyMatch(user -> user.id().equals(id));
  }
}
