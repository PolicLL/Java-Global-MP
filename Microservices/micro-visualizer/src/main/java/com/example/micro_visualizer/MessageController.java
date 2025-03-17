package com.example.micro_visualizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

  @Autowired
  private MessageRepository messageRepository;

  @GetMapping
  public List<Message> getMessages() {
    return messageRepository.findAll();
  }
}
