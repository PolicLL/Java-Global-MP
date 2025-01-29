package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.dto.EventDto;
import com.example.demo.dto.TicketDto;
import com.example.demo.dto.UserDto;
import com.example.demo.facade.BookingFacade;
import com.example.demo.facade.BookingFacadeImpl;
import com.example.demo.mapper.EventMapper;
import com.example.demo.mapper.TicketMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EventService;
import com.example.demo.service.TicketService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
class TicketServiceTest {

  private BookingFacade bookingFacade;
  private TicketDto ticketDto;
  private UserDto userDto;
  private EventDto eventDto;

  @BeforeEach
  void setUp() {
    userDto = UserDto.builder()
        .id("User Id 1")
        .name("John Doe")
        .email("john@example.com")
        .build();

    ticketDto = TicketDto.builder()
        .id("Ticket Id")
        .seatNumber(1)
        .userId("User Id 1")
        .eventId("Event Id 1")
        .build();

    eventDto = EventDto.builder()
        .id("Event Id 1")
        .title("Pera Peric")
        .build();

    UserRepository userRepository = new UserRepository();
    TicketRepository ticketRepository = new TicketRepository();
    EventRepository eventRepository = new EventRepository();

    TicketService ticketService = new TicketService(
        ticketRepository, TicketMapper.INSTANCE, eventRepository, userRepository);

    UserService userService = new UserService(userRepository, UserMapper.INSTANCE);

    EventService eventService = new EventService(eventRepository, EventMapper.INSTANCE);

    bookingFacade = new BookingFacadeImpl(userService, eventService, ticketService);
  }

  @Test
  void createTicket() {
    User createdUser = bookingFacade.createUser(userDto);
    Event createdEvent = bookingFacade.createEvent(eventDto);

    TicketDto ticketDto = TicketDto.builder()
        .eventId(createdEvent.id())
        .userId(createdUser.id())
        .seatNumber(100)
        .build();

    Ticket createdTicket = bookingFacade.bookTicket(ticketDto);

    assertNotNull(createdTicket);
    assertEquals(ticketDto.seatNumber(), createdTicket.seatNumber());
    assertNotNull(bookingFacade.getTicket(createdTicket.id()));
  }

  @Test
  void getTicket() {
    Ticket createdTicket = bookingFacade.bookTicket(ticketDto);

    Ticket foundTicket = bookingFacade.getTicket(createdTicket.id());

    assertNotNull(foundTicket);
    assertEquals(ticketDto.seatNumber(), foundTicket.seatNumber());
  }

  @Test
  void updateTicket() {
    Ticket createdTicket = bookingFacade.bookTicket(ticketDto);

    TicketDto updatedTicketDto = TicketDto.builder()
        .id(createdTicket.id())
        .seatNumber(100)
        .build();

    Ticket updatedTicket = bookingFacade.updateTicket(updatedTicketDto);

    assertNotNull(updatedTicket);
    assertEquals(100, updatedTicket.seatNumber());
  }

  @Test
  void deleteTicket() {
    Ticket createdTicket = bookingFacade.bookTicket(ticketDto);

    bookingFacade.cancelTicket(createdTicket.id());

    assertNull(bookingFacade.getTicket(createdTicket.id()));
  }
}
