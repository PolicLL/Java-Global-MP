package com.example.demo.facade;

import com.example.demo.dto.EventDto;
import com.example.demo.dto.TicketDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;

public interface BookingFacade {


  // EVENT

  Event createEvent(EventDto event);

  Event getEvent(String id);

  Event updateEvent(EventDto eventDto);

  void deleteEvent(String id);

  // USER

  User createUser(User user);

  User getUser(String id);

  User updateUser(UserDto userDto);

  void deleteUser(String id);

  // TICKET

  Ticket bookTicket(TicketDto ticketDto);

  Ticket getTicket(String id);

  Ticket updateTicket(TicketDto ticketDto);

  void cancelTicket(String id);
}
