package com.example.SpringBoot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.SpringBoot.utils.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class AdminControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldReturnAdminHomePage() throws Exception {
    String token = new AuthService(mockMvc).getAuthToken("admin", "admin");
    mockMvc.perform(get("/admin")
            .header("Authorization", "Bearer " + token))
        .andExpect(status().isOk())
        .andExpect(content().string("Admin Home Page"));

  }

  @Test
  void shouldReturnForbiddenForNonAdminUser() throws Exception {
    String token = new AuthService(mockMvc).getAuthToken("manager", "manager");
    mockMvc.perform(get("/admin")
        .header("Authorization", "Bearer " + token))
        .andExpect(status().isForbidden());
  }

  @Test
  void shouldDenyAccessForUnauthenticatedUser() throws Exception {
    mockMvc.perform(get("/admin"))
        .andExpect(status().isUnauthorized());

  }

}
