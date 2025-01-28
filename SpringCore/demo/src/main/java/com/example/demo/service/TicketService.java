package com.example.demo.service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import java.util.List;

public class TicketService {
  private final TicketRepository ticketRepository;

  public TicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  public Ticket bookTicket(Ticket ticket) {
    ticketRepository.save(ticket, "");
    return ticket;
  }

  public Ticket getTicket(String id) {
    return ticketRepository.findById(id);
  }

  public List<Ticket> getAllTickets() {
    return ticketRepository.findAll();
  }

  public void cancelTicket(String id) {
    ticketRepository.deleteById(id);
  }
}
