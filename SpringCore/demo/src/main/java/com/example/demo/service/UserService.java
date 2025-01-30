package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserWithEmailAlreadyExists;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    logger.info("UserService created with UserRepository: {}", userRepository);
  }

  public User createUser(UserDto userDto) {
    User user = userMapper.toModel(userDto);

    if (userRepository.doesUserByEmailExists(userDto.email())) {
      throw new UserWithEmailAlreadyExists(userDto.email());
    }

    userRepository.save(user, user.id());
    return user;
  }

  public User updateUser(UserDto userDto) {
    User updatedUser = userMapper.toModel(userDto);
    userRepository.update(updatedUser, updatedUser.id());
    return updatedUser;
  }

  public User getUser(String id) {
    return userRepository.findById(id);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }

}
