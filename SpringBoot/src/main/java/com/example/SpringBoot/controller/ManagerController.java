package com.example.SpringBoot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {

  @GetMapping
  @PreAuthorize("hasRole('MANAGER')")
  public String home() {
    return "Manager Home Page";
  }

}
