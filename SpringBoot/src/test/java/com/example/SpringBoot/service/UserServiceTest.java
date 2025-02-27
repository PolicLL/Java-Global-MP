package com.example.SpringBoot.service;

import com.example.SpringBoot.model.User;
import com.example.SpringBoot.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetUserById() {
    User user = new User();
    user.setId(1L);
    user.setUsername("testUser");

    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    Optional<User> foundUser = userService.getUserById(1L);

    assertThat(foundUser).isPresent();
    assertThat(foundUser.get().getUsername()).isEqualTo("testUser");
  }

  @Test
  void testDeleteUser() {
    long userId = 1L;
    doNothing().when(userRepository).deleteById(userId);

    userService.deleteUser(userId);

    verify(userRepository, times(1)).deleteById(userId);
  }
}
