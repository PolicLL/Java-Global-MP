package com.example.SpringBoot.utils;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class AuthService {

  private final MockMvc mockMvc;

  @Autowired
  public AuthService(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  public String getAuthToken(String username, String password) throws Exception {
    // Create the JSON request body with username and password
    String jsonRequestBody = "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }";

    // Perform the login request
    String response = mockMvc.perform(MockMvcRequestBuilders
            .post("/user/login")
            .contentType(MediaType.APPLICATION_JSON)  // Set Content-Type to application/json
            .content(jsonRequestBody))  // Send the request body with username and password
        .andExpect(status().isOk())  // Expect status 200 OK
        .andReturn()
        .getResponse()
        .getContentAsString();  // Get response as string

    return response;  // Return the response which contains the token
  }
}
