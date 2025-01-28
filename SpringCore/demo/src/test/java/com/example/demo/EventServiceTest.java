package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.dto.EventDto;
import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
class EventServiceTest {

  private EventService eventService;
  private EventRepository eventRepository;
  private EventDto eventDto;

  @BeforeEach
  void setUp() {
    eventDto = EventDto.builder()
        .id("1")
        .title("Event Name")
        .date("Event Description")
        .build();
    eventRepository = new EventRepository();
    eventService = new EventService(eventRepository);
  }

  @Test
  void createEvent() {
    Event createdEvent = eventService.createEvent(eventDto);

    assertNotNull(createdEvent);
    assertEquals(eventDto.title(), createdEvent.title());
    assertNotNull(eventRepository.findById(createdEvent.id()));
  }

//  @Test
//  void getEvent() {
//    when(eventRepository.findById("Test")).thenReturn(event);
//
//    Event foundEvent = eventService.getEvent("Test");
//
//    assertNotNull(foundEvent);
//    assertEquals(event.title(), foundEvent.title());
//  }
//
//  @Test
//  void updateEvent() {
//    Event newEvent = Event.builder()
//        .title("")
//        .build();
//
//
//    when(eventRepository.findById("Test")).thenReturn(newEvent);
//    when(eventRepository.save(any(Event.class))).thenReturn("Test");
//
//    Event updatedEvent = eventService.updateEvent(1L, event);
//
//    assertNotNull(updatedEvent);
//    assertEquals("Updated Event", updatedEvent.getName());
//  }
//
//  @Test
//  void deleteEvent() {
//    when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
//
//    eventService.deleteEvent(1L);
//
//    verify(eventRepository, times(1)).deleteById(1L);
//  }
}
