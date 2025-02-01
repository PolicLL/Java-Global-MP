package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.Getter;

@Getter
public class Storage<T> {

  private static final Logger logger = LoggerFactory.getLogger(Storage.class);

  private final Map<String, T> storage = new HashMap<>();

  public void save(T entity, String id) {
    logger.info("Saving entity with ID: {}", id);
    storage.put(id, entity);
    logger.info("Entity saved with ID: {}", id);
  }

  public T findById(String id) {
    logger.debug("Finding entity with ID: {}", id);
    T entity = storage.get(id);
    if (entity != null) {
      logger.info("Entity found with ID: {}", id);
    } else {
      logger.warn("Entity not found with ID: {}", id);
    }
    return entity;
  }

  public void deleteById(String id) {
    logger.info("Deleting entity with ID: {}", id);
    storage.remove(id);
    logger.info("Entity deleted with ID: {}", id);
  }

  public void loadData(Map<String, T> data) {
    logger.info("Loading data into storage");
    this.storage.putAll(data);
    logger.info("Data loaded into storage. Total entities: {}", storage.size());
  }
}
