package com.example.demo.repository;

import com.example.demo.model.Event;

public class EventRepository extends GenericRepository<Event> {

  public EventRepository(Storage<Event> storage) {
    super(storage);
  }

  public boolean doesEventExistsById(String id) {
    return storage.getStorage().values().stream().anyMatch(event -> event.id().equals(id));
  }
}
