package com.example.micro_collector.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

  @Bean
  public MeterRegistry meterRegistry() {
    return new SimpleMeterRegistry(); // Or any other registry type you prefer
  }
}
