package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.dto.TicketDto;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import java.util.List;

public class TicketService {
  private final TicketRepository ticketRepository;

  public TicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  public Ticket bookTicket(TicketDto ticketDto) {
    Ticket ticket = Ticket.builder()
        .id(ticketDto.id())
        .userId(ticketDto.userId())
        .eventId(ticketDto.eventId())
        .seatNumber(ticketDto.seatNumber())
        .build();
    ticketRepository.save(ticket, ticket.id());
    return ticket;
  }

  public Ticket updateTicket(TicketDto ticketDto) {
    Ticket updatedTicket = Ticket.builder()
        .seatNumber(ticketDto.seatNumber())
        .eventId(ticketDto.eventId())
        .userId(ticketDto.userId())
        .build();

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
