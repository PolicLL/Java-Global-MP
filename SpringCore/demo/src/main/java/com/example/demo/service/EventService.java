package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import java.util.List;

public class EventService {
  private final EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public Event createEvent(Event event) {
    eventRepository.save(event.id(), event);
    return event;
  }

  public Event getEvent(Long id) {
    return eventRepository.findById(id);
  }

  public List<Event> getAllEvents() {
    return eventRepository.findAll();
  }

  public void deleteEvent(Long id) {
    eventRepository.deleteById(id);
  }
}
