package com.example.micro_collector.recipient;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "micro-recipient")
public interface MicroRecipientClient {

  @GetMapping("/message")
  List<String> getMessage();
}
