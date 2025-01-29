package com.example.demo.service;

import com.example.demo.dto.TicketDto;
import com.example.demo.exception.EventNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.TicketMapper;
import com.example.demo.model.Ticket;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import java.util.List;

public class TicketService {
  private final TicketRepository ticketRepository;
  private final TicketMapper ticketMapper;

  private final EventRepository eventRepository;

  private final UserRepository userRepository;

  public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper,
      EventRepository eventRepository, UserRepository userRepository) {
    this.ticketRepository = ticketRepository;
    this.ticketMapper = ticketMapper;
    this.eventRepository = eventRepository;
    this.userRepository = userRepository;
  }

  public Ticket bookTicket(TicketDto ticketDto) {
    Ticket ticket = ticketMapper.toModel(ticketDto);

    if (!userRepository.doesUserByIdExists(ticketDto.userId())) {
      throw new UserNotFoundException(ticketDto.userId());
    }

    if (!eventRepository.doesEventExistsById(ticketDto.eventId())) {
      throw new EventNotFoundException(ticketDto.eventId());
    }

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
