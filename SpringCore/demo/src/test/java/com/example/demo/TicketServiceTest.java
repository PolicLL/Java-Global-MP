package com.example.demo;


import static org.junit.jupiter.api.Assertions.*;

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
        .name("Name 1")
        .email("Email 1")
        .build();

    TicketService ticketService = new TicketService(new TicketRepository());
    bookingFacade = new BookingFacadeImpl(null, null, ticketService);
  }

  @Test
  void createTicket() {
    Ticket createdTicket = bookingFacade.bookTicket(ticketDto);

    assertNotNull(createdTicket);
    assertEquals(ticketDto.seat(), createdTicket.seat());
    assertNotNull(bookingFacade.getTicket(createdTicket.id()));
  }

  @Test
  void getTicket() {
    Ticket createdTicket = bookingFacade.createTicket(ticketDto);

    Ticket foundTicket = bookingFacade.getTicket(createdTicket.id());

    assertNotNull(foundTicket);
    assertEquals(ticketDto.seat(), foundTicket.seat());
  }

  @Test
  void updateTicket() {
    Ticket createdTicket = bookingFacade.createTicket(ticketDto);

    TicketDto updatedTicketDto = TicketDto.builder()
        .id(createdTicket.id())
        .eventId("2")
        .userId("1")
        .seat("B1")
        .build();

    Ticket updatedTicket = bookingFacade.updateTicket(updatedTicketDto);

    assertNotNull(updatedTicket);
    assertEquals("B1", updatedTicket.seat());
  }

  @Test
  void deleteTicket() {
    Ticket createdTicket = bookingFacade.createTicket(ticketDto);

    bookingFacade.deleteTicket(createdTicket.id());

    assertNull(bookingFacade.getTicket(createdTicket.id()));
  }
}
