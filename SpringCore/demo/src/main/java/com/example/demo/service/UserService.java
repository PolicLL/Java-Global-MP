package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserWithEmailAlreadyExists;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class UserService {
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    logger.info("UserService created with UserRepository: {}", userRepository);
  }

  public User createUser(UserDto userDto) {
    logger.info("Creating user with email: {}", userDto.email());
    if (userRepository.doesUserByEmailExists(userDto.email())) {
      logger.warn("User with email: {} already exists", userDto.email());
      throw new UserWithEmailAlreadyExists(userDto.email());
    }

    User user = userMapper.toModel(userDto);
    userRepository.save(user, user.id());
    logger.info("User created with ID: {}", user.id());
    return user;
  }

  public User updateUser(UserDto userDto) {
    logger.info("Updating user with ID: {}", userDto.id());
    User updatedUser = userMapper.toModel(userDto);
    userRepository.update(updatedUser, updatedUser.id());
    logger.info("User updated with ID: {}", updatedUser.id());
    return updatedUser;
  }

  public User getUser(String id) {
    logger.debug("Fetching user with ID: {}", id);
    User user = userRepository.findById(id);
    if (user != null) {
      logger.info("User found with ID: {}", id);
    } else {
      logger.warn("User not found with ID: {}", id);
    }
    return user;
  }

  public List<User> getAllUsers() {
    logger.debug("Fetching all users");
    List<User> users = userRepository.findAll();
    logger.info("Found {} users", users.size());
    return users;
  }

  public void deleteUser(String id) {
    logger.info("Deleting user with ID: {}", id);
    userRepository.deleteById(id);
    logger.info("User deleted with ID: {}", id);
  }
}
