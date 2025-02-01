package com.example.demo.facade;

import com.example.demo.dto.EventDto;
import com.example.demo.dto.TicketDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.service.EventService;
import com.example.demo.service.TicketService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingFacadeImpl implements BookingFacade {

  private static final Logger logger = LoggerFactory.getLogger(BookingFacadeImpl.class);

  private final UserService userService;
  private final EventService eventService;
  private final TicketService ticketService;

  public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService) {
    this.userService = userService;
    this.eventService = eventService;
    this.ticketService = ticketService;

    logger.info("BookingFacadeImpl created with services: UserService={}, EventService={}, TicketService={}",
        userService, eventService, ticketService);
  }

  // USER

  @Override
  public User createUser(UserDto user) {
    logger.info("Creating user with ID: {}", user.id());
    User createdUser = userService.createUser(user);
    logger.info("User created with ID: {}", createdUser.id());
    return createdUser;
  }

  @Override
  public User getUser(String id) {
    logger.info("Fetching user with ID: {}", id);
    User foundUser = userService.getUser(id);
    if (foundUser != null) {
      logger.info("User found with ID: {}", id);
    } else {
      logger.warn("User not found with ID: {}", id);
    }
    return foundUser;
  }

  @Override
  public User updateUser(UserDto userDto) {
    logger.info("Updating user with ID: {}", userDto.id());
    User updatedUser = userService.updateUser(userDto);
    logger.info("User updated with ID: {}", updatedUser.id());
    return updatedUser;
  }

  @Override
  public void deleteUser(String id) {
    logger.info("Deleting user with ID: {}", id);
    userService.deleteUser(id);
    logger.info("User deleted with ID: {}", id);
  }

  // EVENT

  @Override
  public Event createEvent(EventDto eventDto) {
    logger.info("Creating event with ID: {}", eventDto.id());
    Event createdEvent = eventService.createEvent(eventDto);
    logger.info("Event created with ID: {}", createdEvent.id());
    return createdEvent;
  }

  @Override
  public Event getEvent(String id) {
    logger.info("Fetching event with ID: {}", id);
    Event foundEvent = eventService.getEvent(id);
    if (foundEvent != null) {
      logger.info("Event found with ID: {}", id);
    } else {
      logger.warn("Event not found with ID: {}", id);
    }
    return foundEvent;
  }

  @Override
  public Event updateEvent(EventDto eventDto) {
    logger.info("Updating event with ID: {}", eventDto.id());
    Event updatedEvent = eventService.updateEvent(eventDto);
    logger.info("Event updated with ID: {}", updatedEvent.id());
    return updatedEvent;
  }

  @Override
  public void deleteEvent(String id) {
    logger.info("Deleting event with ID: {}", id);
    eventService.deleteEvent(id);
    logger.info("Event deleted with ID: {}", id);
  }

  // TICKET

  @Override
  public Ticket bookTicket(TicketDto ticketDto) {
    logger.info("Booking ticket for user with ID: {} and event ID: {}", ticketDto.userId(), ticketDto.eventId());
    Ticket bookedTicket = ticketService.bookTicket(ticketDto);
    logger.info("Ticket booked with ID: {}", bookedTicket.id());
    return bookedTicket;
  }

  @Override
  public Ticket getTicket(String id) {
    logger.info("Fetching ticket with ID: {}", id);
    Ticket foundTicket = ticketService.getTicket(id);
    if (foundTicket != null) {
      logger.info("Ticket found with ID: {}", id);
    } else {
      logger.warn("Ticket not found with ID: {}", id);
    }
    return foundTicket;
  }

  @Override
  public Ticket updateTicket(TicketDto ticketDto) {
    logger.info("Updating ticket with ID: {}", ticketDto.id());
    Ticket updatedTicket = ticketService.updateTicket(ticketDto);
    logger.info("Ticket updated with ID: {}", updatedTicket.id());
    return updatedTicket;
  }

  @Override
  public void cancelTicket(String id) {
    logger.info("Canceling ticket with ID: {}", id);
    ticketService.cancelTicket(id);
    logger.info("Ticket canceled with ID: {}", id);
  }
}
