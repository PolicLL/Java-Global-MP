package com.example.SpringBoot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.SpringBoot.utils.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private String token;

  @BeforeEach
  void setup() throws Exception {
    this.token = new AuthService(mockMvc).getAuthToken("user", "user");
  }

  @Test
  void shouldReturnUserHomePage() throws Exception {
    mockMvc.perform(get("/user/home")
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(content().string("User Home Page"));
  }

  @Test
  void shouldDenyAccessForUnauthenticatedUser() throws Exception {
    mockMvc.perform(get("/user/home"))
        .andExpect(status().isUnauthorized());
  }


  @Test
  void shouldReturnLoginResponse() throws Exception {
    String loginPayload = "{\"username\": \"user\", \"password\": \"user\"}";
    mockMvc.perform(post("/user/login")
            .contentType("application/json")
            .content(loginPayload))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnUserAfterRegistration() throws Exception {
    String registrationPayload = "{\"username\": \"newuser\", \"password\": \"newpassword\"}";
    mockMvc.perform(post("/user/register")
            .contentType("application/json")
            .content(registrationPayload))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnAllUsers() throws Exception {
    String response = mockMvc.perform(
            get("/user")  // Endpoint to get all users
            .header("Authorization", "Bearer " + token))  // Add Bearer token for authorization
        .andExpect(status().isOk())  // Expect status 200 OK
        .andReturn()
        .getResponse()
        .getContentAsString();

    System.out.println(response);
  }

  @Test
  void shouldReturnForbiddenForNonUserUser() throws Exception {
    String newToken = new AuthService(mockMvc).getAuthToken("manager", "manager");
    mockMvc.perform(get("/user/home")
            .header("Authorization", "Bearer " + newToken))
        .andExpect(status().isForbidden());
  }


  @Test
  void shouldReturnUserById() throws Exception {
    String token = new AuthService(mockMvc).getAuthToken("user", "user");
    mockMvc.perform(get("/user/4")
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnUpdatedUser() throws Exception {
    String token = new AuthService(mockMvc).getAuthToken("user", "user");
    String updatePayload = "{\"username\": \"updateduser\", \"password\": \"updatedpassword\"}";
    mockMvc.perform(put("/user/4")
            .header("Authorization", "Bearer " + token)
            .contentType("application/json")
            .content(updatePayload))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnNoContentAfterDelete() throws Exception {
    String token = new AuthService(mockMvc).getAuthToken("admin", "admin");
    mockMvc.perform(delete("/user/5")
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isNoContent());
  }
}
