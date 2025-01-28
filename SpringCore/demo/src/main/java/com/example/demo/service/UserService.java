package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.List;

public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {
    userRepository.save(user.id(), user);
    return user;
  }

  public User getUser(Long id) {
    return userRepository.findById(id);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
