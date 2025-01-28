package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericRepository<T> {
  private final Map<Long, T> storage = new HashMap<>();

  public void save(Long id, T entity) {
    storage.put(id, entity);
  }

  public T findById(Long id) {
    return storage.get(id);
  }

  public List<T> findAll() {
    return new ArrayList<>(storage.values());
  }

  public void deleteById(Long id) {
    storage.remove(id);
  }
}
