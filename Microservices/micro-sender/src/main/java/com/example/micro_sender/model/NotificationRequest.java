package com.example.micro_sender.model;

import lombok.Data;

@Data
public class NotificationRequest {
  private String user;
  private String message;
}