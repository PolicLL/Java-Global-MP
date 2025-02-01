package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.dto.EventDto;
import com.example.demo.facade.BookingFacade;
import com.example.demo.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@ContextConfiguration(locations = "classpath:applicationContext.xml")
class EventServiceTest {

  @Autowired
  private BookingFacade bookingFacade;
  private EventDto eventDto;

  @BeforeEach
  void setUp() {
    eventDto = EventDto.builder()
        .id("1")
        .title("Event Name")
        .date("Event Description")
        .build();
  }

  @Test
  void createEvent() {
    Event createdEvent = bookingFacade.createEvent(eventDto);

    assertNotNull(createdEvent);
    assertEquals(eventDto.title(), createdEvent.title());
    assertNotNull(bookingFacade.getEvent(createdEvent.id()));
  }

  @Test
  void getEvent() {
    Event createdEvent = bookingFacade.createEvent(eventDto);

    Event foundEvent = bookingFacade.getEvent(createdEvent.id());

    assertNotNull(foundEvent);
    assertEquals(eventDto.title(), foundEvent.title());
  }

  @Test
  void updateEvent() {
    Event createdEvent = bookingFacade.createEvent(eventDto);

    EventDto updateEventDto = EventDto.builder()
        .id(createdEvent.id())
        .title("Updated Name")
        .date("Event Description")
        .build();

    Event updatedEvent = bookingFacade.updateEvent(updateEventDto);

    assertNotNull(updatedEvent);
    assertEquals("Updated Name", updatedEvent.title());
  }

  @Test
  void deleteEvent() {
    Event createdEvent = bookingFacade.createEvent(eventDto);

    bookingFacade.deleteEvent(createdEvent.id());

    assertNull(bookingFacade.getEvent(createdEvent.id()));
  }
}
