package com.example.micro_collector.recipient;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// http://consumer.default.svc.cluster.local:8081/api/message

@FeignClient(name = "micro-recipient", url = "http://micro-recipient.default.svc.cluster.local:8081")
public interface MicroRecipientClient {

  @GetMapping("/message")
  List<String> getMessage();
}
