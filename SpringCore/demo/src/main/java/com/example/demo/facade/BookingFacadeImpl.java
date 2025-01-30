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

  public BookingFacadeImpl(UserService userService, EventService eventService,
      TicketService ticketService) {
    this.userService = userService;
    this.eventService = eventService;
    this.ticketService = ticketService;

    logger.info("BookingFacadeImpl created with services: UserService={}, EventService={}, TicketService={}",
        userService, eventService, ticketService);
  }

  // USER

  @Override
  public User createUser(UserDto user) {
    return userService.createUser(user);
  }

  @Override
  public User getUser(String id) {
    return userService.getUser(id);
  }

  @Override
  public User updateUser(UserDto userDto) {
    return userService.updateUser(userDto);
  }

  @Override
  public void deleteUser(String id) {
    userService.deleteUser(id);
  }


  // EVENT

  @Override
  public Event createEvent(EventDto eventDto) {
    return eventService.createEvent(eventDto);
  }

  @Override
  public Event getEvent(String id) {
    return eventService.getEvent(id);
  }

  @Override
  public Event updateEvent(EventDto eventDto) {
    return eventService.updateEvent(eventDto);
  }

  @Override
  public void deleteEvent(String id) {
    eventService.deleteEvent(id);
  }

  // TICKET

  @Override
  public Ticket bookTicket(TicketDto ticketDto) {
    return ticketService.bookTicket(ticketDto);
  }

  @Override
  public Ticket getTicket(String id) {
    return ticketService.getTicket(id);
  }

  @Override
  public Ticket updateTicket(TicketDto ticketDto) {
    return ticketService.updateTicket(ticketDto);
  }

  @Override
  public void cancelTicket(String id) {
    ticketService.cancelTicket(id);
  }
}
