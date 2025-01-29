package com.example.demo.repository;

import com.example.demo.model.User;

public class UserRepository extends GenericRepository<User> {

  public UserRepository(Storage<User> storage) {
    super(storage);
  }

  public boolean doesUserByEmailExists(String email) {
    return storage.getStorage().values().stream().anyMatch(user -> user.email().equals(email));
  }

  public boolean doesUserByIdExists(String id) {
    return storage.getStorage().values().stream().anyMatch(user -> user.id().equals(id));
  }
}
