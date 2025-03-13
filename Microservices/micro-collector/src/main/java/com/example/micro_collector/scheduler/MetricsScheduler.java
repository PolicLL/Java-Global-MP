package com.example.micro_collector.scheduler;

import com.example.micro_collector.service.MyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MetricsScheduler {

  private final MyService myService;

  public MetricsScheduler(MyService myService) {
    this.myService = myService;
  }

  @Scheduled(fixedRate = 10000) // Every 10 seconds
  public void scheduleRandomMetrics() {
    myService.generateRandomMetrics();
  }
}
