package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

public class Storage<T> {
  private final Map<String, T> storage = new HashMap<>();

  public void save(T entity, String id) {
    storage.put(id, entity);
  }

  public T findById(String id) {
    return storage.get(id);
  }

  public void deleteById(String id) {
    storage.remove(id);
  }

  public Map<String, T> getStorage() {
    return storage;
  }

  public void loadData(Map<String, T> data) {
    this.storage.putAll(data);
  }
}
