package com.example.SpringBoot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.SpringBoot.model.User;
import com.example.SpringBoot.security.filter.JwtFilter;
import com.example.SpringBoot.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;



@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.flyway.enabled=false")
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private UserService userService;

  @MockitoBean
  private JwtFilter jwtFilter;

  @InjectMocks
  private UserController userController;


  @Test
  @WithMockUser(username = "user", authorities = {"ROLE_USER"})
  void testGetUserById() throws Exception {
    User user = new User();
    user.setId(1L);
    user.setUsername("testUser");

    when(userService.getUserById(1L)).thenReturn(java.util.Optional.of(user));

    mockMvc.perform(get("/user/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType("text/plain;charset=UTF-8"));
  }

}
