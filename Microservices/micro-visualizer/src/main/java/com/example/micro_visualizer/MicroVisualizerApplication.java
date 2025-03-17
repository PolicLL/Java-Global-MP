package com.example.micro_visualizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.micro_visualizer")
public class MicroVisualizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroVisualizerApplication.class, args);
	}

}
