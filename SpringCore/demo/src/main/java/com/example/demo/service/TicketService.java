package com.example.demo.service;

import com.example.demo.dto.TicketDto;
import com.example.demo.exception.EventNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.TicketMapper;
import com.example.demo.model.Ticket;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class TicketService {
  private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

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
    logger.info("TicketService created with TicketRepository: {}, EventRepository: {}, UserRepository: {}",
        ticketRepository, eventRepository, userRepository);
  }

  public Ticket bookTicket(TicketDto ticketDto) {
    logger.info("Booking ticket for event ID: {} by user ID: {}", ticketDto.eventId(), ticketDto.userId());
    if (!userRepository.doesUserByIdExists(ticketDto.userId())) {
      logger.warn("User with ID: {} not found", ticketDto.userId());
      throw new UserNotFoundException(ticketDto.userId());
    }

    if (!eventRepository.doesEventExistsById(ticketDto.eventId())) {
      logger.warn("Event with ID: {} not found", ticketDto.eventId());
      throw new EventNotFoundException(ticketDto.eventId());
    }

    Ticket ticket = ticketMapper.toModel(ticketDto);
    ticketRepository.save(ticket, ticket.id());
    logger.info("Ticket booked with ID: {}", ticket.id());
    return ticket;
  }

  public Ticket updateTicket(TicketDto ticketDto) {
    logger.info("Updating ticket with ID: {}", ticketDto.id());
    Ticket updatedTicket = ticketMapper.toModel(ticketDto);
    ticketRepository.update(updatedTicket, updatedTicket.id());
    logger.info("Ticket updated with ID: {}", updatedTicket.id());
    return updatedTicket;
  }

  public Ticket getTicket(String id) {
    logger.debug("Fetching ticket with ID: {}", id);
    Ticket ticket = ticketRepository.findById(id);
    if (ticket != null) {
      logger.info("Ticket found with ID: {}", id);
    } else {
      logger.warn("Ticket not found with ID: {}", id);
    }
    return ticket;
  }

  public List<Ticket> getAllTickets() {
    logger.debug("Fetching all tickets");
    List<Ticket> tickets = ticketRepository.findAll();
    logger.info("Found {} tickets", tickets.size());
    return tickets;
  }

  public void cancelTicket(String id) {
    logger.info("Cancelling ticket with ID: {}", id);
    ticketRepository.deleteById(id);
    logger.info("Ticket cancelled with ID: {}", id);
  }
}
