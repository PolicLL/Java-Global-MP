//package com.example.demo;
//
//
//import com.example.demo.model.Ticket;
//import com.example.demo.repository.TicketRepository;
//import com.example.demo.service.TicketService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TicketServiceTest {
//  private TicketService ticketService;
//
//  @BeforeEach
//  void setUp() {
//    ticketService = new TicketService(new TicketRepository());
//  }
//
//  @Test
//  void testBookTicket() {
//    Ticket ticket = new Ticket(1L, 1L, 1L, 15);
//    ticketService.bookTicket(ticket);
//
//    assertEquals(ticket, ticketService.getTicket(1L));
//  }
//
//  @Test
//  void testCancelTicket() {
//    Ticket ticket = new Ticket(1L, 1L, 1L, 15);
//    ticketService.bookTicket(ticket);
//
//    ticketService.cancelTicket(1L);
//    assertNull(ticketService.getTicket(1L));
//  }
//}
