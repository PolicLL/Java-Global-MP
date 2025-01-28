package com.example.demo.facade;

import com.example.demo.dto.EventDto;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;

public interface BookingFacade {
  User createUser(User user);

  Event createEvent(EventDto event);

  Ticket bookTicket(Ticket ticket);

  User getUser(String id);

  Event getEvent(String id);

  Ticket getTicket(String id);

  void cancelTicket(String id);
}
