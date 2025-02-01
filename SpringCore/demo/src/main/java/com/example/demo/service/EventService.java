package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.mapper.EventMapper;
import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class EventService {
  private static final Logger logger = LoggerFactory.getLogger(EventService.class);

  private final EventRepository eventRepository;
  private final EventMapper eventMapper;

  public EventService(EventRepository eventRepository, EventMapper eventMapper) {
    this.eventRepository = eventRepository;
    this.eventMapper = eventMapper;
    logger.info("EventService created with EventRepository: {}", eventRepository);
  }

  public Event createEvent(EventDto eventDto) {
    logger.info("Creating new event with title: {}", eventDto.title());
    Event newEvent = eventMapper.toModel(eventDto);
    eventRepository.save(newEvent, newEvent.id());
    logger.info("Event created with ID: {}", newEvent.id());
    return newEvent;
  }

  public Event updateEvent(EventDto updateEventDto) {
    logger.info("Updating event with ID: {}", updateEventDto.id());
    Event updatedEvent = eventMapper.toModel(updateEventDto);
    eventRepository.update(updatedEvent, updatedEvent.id());
    logger.info("Event updated with ID: {}", updatedEvent.id());
    return updatedEvent;
  }

  public Event getEvent(String id) {
    logger.debug("Fetching event with ID: {}", id);
    Event event = eventRepository.findById(id);
    if (event != null) {
      logger.info("Event found with ID: {}", id);
    } else {
      logger.warn("Event not found with ID: {}", id);
    }
    return event;
  }

  public List<Event> getAllEvents() {
    logger.debug("Fetching all events");
    List<Event> events = eventRepository.findAll();
    logger.info("Found {} events", events.size());
    return events;
  }

  public void deleteEvent(String id) {
    logger.info("Deleting event with ID: {}", id);
    eventRepository.deleteById(id);
    logger.info("Event deleted with ID: {}", id);
  }
}
