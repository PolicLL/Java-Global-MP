package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.dto.TicketDto;
import com.example.demo.facade.BookingFacade;
import com.example.demo.facade.BookingFacadeImpl;
import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
class TicketServiceTest {

  private BookingFacade bookingFacade;
  private TicketDto ticketDto;

  @BeforeEach
  void setUp() {
    ticketDto = TicketDto.builder()
        .id("Ticket Id")
        .seatNumber(1)
        .userId("User Id 1")
        .build();

    TicketService ticketService = new TicketService(new TicketRepository());
    bookingFacade = new BookingFacadeImpl(null, null, ticketService);
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
