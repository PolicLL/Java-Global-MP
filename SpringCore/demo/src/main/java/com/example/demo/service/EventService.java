package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import java.util.List;
import java.util.UUID;

public class EventService {
  private final EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public Event createEvent(EventDto event) {

    Event newEvent = Event.builder()
        .title(event.title())
        .date(event.date())
        .id(UUID.randomUUID().toString())
        .build();

    eventRepository.save(newEvent, newEvent.id());
    return newEvent;
  }

  public Event getEvent(String id) {
    return eventRepository.findById(id);
  }

  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  public void deleteEvent(String id) {
    eventRepository.deleteById(id);
  }
}
