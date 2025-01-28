package com.example.demo.facade;

import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.service.EventService;
import com.example.demo.service.TicketService;
import com.example.demo.service.UserService;


public class BookingFacadeImpl implements BookingFacade {
  private final UserService userService;
  private final EventService eventService;
  private final TicketService ticketService;

  public BookingFacadeImpl(UserService userService, EventService eventService,
      TicketService ticketService) {
    this.userService = userService;
    this.eventService = eventService;
    this.ticketService = ticketService;
  }

  @Override
  public User createUser(User user) {
    return userService.createUser(user);
  }

  @Override
  public Event createEvent(Event event) {
    return eventService.createEvent(event);
  }

  @Override
  public Ticket bookTicket(Ticket ticket) {
    return ticketService.bookTicket(ticket);
  }

  @Override
  public User getUser(Long id) {
    return userService.getUser(id);
  }

  @Override
  public Event getEvent(Long id) {
    return eventService.getEvent(id);
  }

  @Override
  public Ticket getTicket(Long id) {
    return ticketService.getTicket(id);
  }

  @Override
  public void cancelTicket(Long id) {
    ticketService.cancelTicket(id);
  }
}
