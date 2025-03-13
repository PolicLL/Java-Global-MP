package com.example.micro_collector.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MyService {

  private final MeterRegistry meterRegistry;
  private final Random random;

  public MyService(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
    this.random = new Random();
  }

  public void generateRandomMetrics() {
    // Randomly generated value between 0 and 100
    int randomValue = random.nextInt(100);

    // Custom metric: Random counter (increments based on randomValue)
    Counter counter = meterRegistry.counter("my_custom_random_counter");
    counter.increment(randomValue);

    // Random gauge (value between 0 and 100)
    meterRegistry.gauge("my_custom_random_gauge", randomValue);

    // Log to confirm metrics creation (optional)
    System.out.println("Generated random value: " + randomValue);
  }
}
