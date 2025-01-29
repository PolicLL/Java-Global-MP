package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.dto.UserDto;
import com.example.demo.facade.BookingFacade;
import com.example.demo.facade.BookingFacadeImpl;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
class UserServiceTest {

  private BookingFacade bookingFacade;
  private UserDto userDto;

  @BeforeEach
  void setUp() {
    userDto = UserDto.builder()
        .id("1")
        .name("John Doe")
        .email("john@example.com")
        .build();

    UserService userService = new UserService(new UserRepository(), UserMapper.INSTANCE);
    bookingFacade = new BookingFacadeImpl(userService, null, null);
  }

  @Test
  void createUser() {
    User createdUser = bookingFacade.createUser(userDto);

    assertNotNull(createdUser);
    assertEquals(userDto.name(), createdUser.name());
    assertNotNull(bookingFacade.getUser(createdUser.id()));
  }

  @Test
  void getUser() {
    User createdUser = bookingFacade.createUser(userDto);

    User foundUser = bookingFacade.getUser(createdUser.id());

    assertNotNull(foundUser);
    assertEquals(userDto.name(), foundUser.name());
  }

  @Test
  void updateUser() {
    User createdUser = bookingFacade.createUser(userDto);

    UserDto updateUserDto = UserDto.builder()
        .id(createdUser.id())
        .name("Updated Name")
        .email("john@example.com")
        .build();

    User updatedUser = bookingFacade.updateUser(updateUserDto);

    assertNotNull(updatedUser);
    assertEquals("Updated Name", updatedUser.name());
  }

  @Test
  void deleteUser() {
    User createdUser = bookingFacade.createUser(userDto);

    bookingFacade.deleteUser(createdUser.id());

    assertNull(bookingFacade.getUser(createdUser.id()));
  }
}
