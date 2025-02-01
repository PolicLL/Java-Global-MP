package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.dto.EventDto;
import com.example.demo.dto.TicketDto;
import com.example.demo.dto.UserDto;
import com.example.demo.facade.BookingFacade;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@ContextConfiguration(locations = "classpath:applicationContext.xml")
class TicketServiceTest {

  @Autowired
  private BookingFacade bookingFacade;
  private TicketDto ticketDto;
  private static int counter = 0;

  @BeforeEach
  void setUp() {
    UserDto userDto = UserDto.builder()
        .id("User Id 1")
        .name("John Doe" + counter)
        .email("john@example.com" + counter++)
        .build();

    EventDto eventDto = EventDto.builder()
        .id("Event Id 1")
        .title("Pera Peric")
        .build();

    User createdUser = bookingFacade.createUser(userDto);
    Event createdEvent = bookingFacade.createEvent(eventDto);

    ticketDto = TicketDto.builder()
        .eventId(createdEvent.id())
        .userId(createdUser.id())
        .seatNumber(100)
        .build();
  }

  @Test
  void createTicket() {
    Ticket createdTicket = bookingFacade.bookTicket(ticketDto);

    assertNotNull(createdTicket);
    assertEquals(ticketDto.seatNumber(), createdTicket.seatNumber());
    assertNotNull(bookingFacade.getTicket(createdTicket.id()));
  }

  @Test
  void getTicket() {
    Ticket createdTicket = bookingFacade.bookTicket(ticketDto);

    Ticket foundTicket = bookingFacade.getTicket(createdTicket.id());

    assertNotNull(foundTicket);
    assertEquals(ticketDto.seatNumber(), foundTicket.seatNumber());
  }

  @Test
  void updateTicket() {
    Ticket createdTicket = bookingFacade.bookTicket(ticketDto);

    TicketDto updatedTicketDto = TicketDto.builder()
        .id(createdTicket.id())
        .seatNumber(100)
        .build();

    Ticket updatedTicket = bookingFacade.updateTicket(updatedTicketDto);

    assertNotNull(updatedTicket);
    assertEquals(100, updatedTicket.seatNumber());
  }

  @Test
  void deleteTicket() {
    Ticket createdTicket = bookingFacade.bookTicket(ticketDto);

    bookingFacade.cancelTicket(createdTicket.id());

    assertNull(bookingFacade.getTicket(createdTicket.id()));
  }
}
