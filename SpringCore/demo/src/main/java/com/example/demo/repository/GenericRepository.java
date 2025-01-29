package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

public class GenericRepository<T> {
  protected Storage<T> storage;

  public GenericRepository(Storage<T> storage) {
    this.storage = storage;
  }

  public void save(T entity, String id) {
    storage.save(entity, id);
  }

  public void update(T updatedEntity, String id) {
    storage.save(updatedEntity, id);
  }

  public T findById(String id) {
    return storage.findById(id);
  }

  public List<T> findAll() {
    return new ArrayList<>(storage.getStorage().values());
  }

  public void deleteById(String id) {
    storage.getStorage().remove(id);
  }
}
