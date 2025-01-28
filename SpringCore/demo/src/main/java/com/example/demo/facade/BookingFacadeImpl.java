package com.example.demo.facade;

import com.example.demo.dto.EventDto;
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
  public Event createEvent(EventDto eventDto) {
    return eventService.createEvent(eventDto);
  }

  @Override
  public Ticket bookTicket(Ticket ticket) {
    return ticketService.bookTicket(ticket);
  }

  @Override
  public User getUser(String id) {
    return userService.getUser(id);
  }

  @Override
  public Event getEvent(String id) {
    return eventService.getEvent(id);
  }

  @Override
  public Ticket getTicket(String id) {
    return ticketService.getTicket(id);
  }

  @Override
  public void cancelTicket(String id) {
    ticketService.cancelTicket(id);
  }
}
