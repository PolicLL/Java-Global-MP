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
    ticketRepository.save(ticket.id(), ticket);
    return ticket;
  }

  public Ticket getTicket(Long id) {
    return ticketRepository.findById(id);
  }

  public List<Ticket> getAllTickets() {
    return ticketRepository.findAll();
  }

  public void cancelTicket(Long id) {
    ticketRepository.deleteById(id);
  }
}
