package com.example.demo.repository;

import com.example.demo.model.Event;

public class EventRepository extends GenericRepository<Event> {

  public boolean doesEventExistsById(String id) {
    return storage.values().stream().anyMatch(event -> event.id().equals(id));
  }

}