package com.example.SpringBoot.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.SpringBoot.repository.UserRepository;
import com.example.SpringBoot.utils.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class AdminControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;


  @Test
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  void shouldReturnAdminHomePage() throws Exception {
    System.out.println("TOKEN: " + new AuthService(mockMvc).getAuthToken("admin", "admin"));
  }






}
