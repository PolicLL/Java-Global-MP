package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class JavaGlobalSpringCoreApplication {

	// Create a logger for this class
	private static final Logger logger = LoggerFactory.getLogger(JavaGlobalSpringCoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JavaGlobalSpringCoreApplication.class, args);

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


		logger.info("Spring Boot Application and XML Context Initialized");

		try {
			Object bookingFacade = context.getBean("bookingFacade");
			logger.info("BookingFacade bean loaded: {}", bookingFacade);
		} catch (Exception e) {
			logger.error("Error loading BookingFacade bean: ", e);
		}
	}
}
