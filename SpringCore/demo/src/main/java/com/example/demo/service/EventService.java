package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.mapper.EventMapper;
import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import java.util.List;

public class EventService {
  private final EventRepository eventRepository;
  private final EventMapper eventMapper;

  public EventService(EventRepository eventRepository, EventMapper eventMapper) {
    this.eventRepository = eventRepository;
    this.eventMapper = eventMapper;
  }

  public Event createEvent(EventDto eventDto) {
    Event newEvent = eventMapper.toModel(eventDto);
    eventRepository.save(newEvent, newEvent.id());
    return newEvent;
  }

  public Event updateEvent(EventDto updateEventDto) {
    Event updatedEvent = eventMapper.toModel(updateEventDto);
    eventRepository.update(updatedEvent, updatedEvent.id());
    return updatedEvent;
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
