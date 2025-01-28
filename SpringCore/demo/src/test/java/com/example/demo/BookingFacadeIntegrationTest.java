package com.example.demo;

import com.example.demo.facade.BookingFacade;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class BookingFacadeIntegrationTest {
  @Test
  void testBookingFlow() {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    BookingFacade facade = context.getBean("bookingFacade", BookingFacade.class);

    // Create user
    User user = new User(1L, "Bob", "bob@example.com");
    facade.createUser(user);

    // Create event
    Event event = new Event(1L, "Rock Concert", "2025-01-28");
    facade.createEvent(event);

    // Book ticket
    Ticket ticket = new Ticket(1L, user.id(), event.id(), 10);
    facade.bookTicket(ticket);

    // Validate
    assertEquals(user, facade.getUser(user.id()));
    assertEquals(event, facade.getEvent(event.id()));
    assertEquals(ticket, facade.getTicket(ticket.id()));

    // Cancel ticket
    facade.cancelTicket(ticket.id());
    assertNull(facade.getTicket(ticket.id()));
  }
}
