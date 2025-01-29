package com.example.demo.repository;

import com.example.demo.model.User;

public class UserRepository extends GenericRepository<User> {

  public boolean doesUserByEmailExists(String email) {
    return storage.values().stream().anyMatch(user -> user.email().equals(email));
  }

  public boolean doesUserByIdExists(String id) {
    return storage.values().stream().anyMatch(user -> user.id().equals(id));
  }

}
