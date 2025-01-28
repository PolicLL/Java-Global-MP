package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GenericRepository<T> {
  private final Map<String, T> storage = new HashMap<>();

    public String save(T entity) {
    String id = UUID.randomUUID().toString();
    storage.put(id, entity);
    return id;
  }

  public T findById(String id) {
    return storage.get(id);
  }

  public List<T> findAll() {
    return new ArrayList<>(storage.values());
  }

  public void deleteById(String id) {
    storage.remove(id);
  }
}
