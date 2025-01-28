package com.example.demo;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

  private UserService userService;

  @BeforeEach
  void setUp() {
    userService = new UserService(new UserRepository());
  }

  @Test
  void testCreateUser() {
    User user = new User(1L, "Alice", "alice@example.com");
    userService.createUser(user);

    assertEquals(user, userService.getUser(1L));
  }

  @Test
  void testDeleteUser() {
    User user = new User(1L, "Alice", "alice@example.com");
    userService.createUser(user);

    userService.deleteUser(1L);
    assertNull(userService.getUser(1L));
  }
}

