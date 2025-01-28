package com.example.demo;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {

  private EventService eventService;

  @BeforeEach
  void setUp() {
    eventService = new EventService(new EventRepository());
  }

  @Test
  void testCreateEvent() {
    Event event = new Event(1L, "Concert", "2025-01-28");
    eventService.createEvent(event);

    assertEquals(event, eventService.getEvent(1L));
  }

  @Test
  void testDeleteEvent() {
    Event event = new Event(1L, "Concert", "2025-01-28");
    eventService.createEvent(event);

    eventService.deleteEvent(1L);
    assertNull(eventService.getEvent(1L));
  }
}
