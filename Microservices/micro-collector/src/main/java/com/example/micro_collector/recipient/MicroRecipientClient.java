package com.example.micro_collector.recipient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "micro-recipient", url = "http://micro-recipient:8082")
public interface MicroRecipientClient {

  @GetMapping("/message")
  String getMessage();
}
