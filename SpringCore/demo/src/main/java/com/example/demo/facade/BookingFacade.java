package com.example.demo.facade;

import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;

public interface BookingFacade {
  User createUser(User user);

  Event createEvent(Event event);

  Ticket bookTicket(Ticket ticket);

  User getUser(Long id);

  Event getEvent(Long id);

  Ticket getTicket(Long id);

  void cancelTicket(Long id);
}
