package com.example.demo.service;

import com.example.demo.dto.TicketDto;
import com.example.demo.mapper.TicketMapper;
import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import java.util.List;

public class TicketService {
  private final TicketRepository ticketRepository;
  private final TicketMapper ticketMapper;

  public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
    this.ticketRepository = ticketRepository;
    this.ticketMapper = ticketMapper;
  }

  public Ticket bookTicket(TicketDto ticketDto) {
    Ticket ticket = ticketMapper.toModel(ticketDto);
    ticketRepository.save(ticket, ticket.id());
    return ticket;
  }

  public Ticket updateTicket(TicketDto ticketDto) {
    Ticket updatedTicket = ticketMapper.toModel(ticketDto);
    ticketRepository.update(updatedTicket, updatedTicket.id());
    return updatedTicket;
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
