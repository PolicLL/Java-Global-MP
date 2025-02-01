package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericRepository<T> {
  protected Storage<T> storage;

  private static final Logger logger = LoggerFactory.getLogger(GenericRepository.class);

  public GenericRepository(Storage<T> storage) {
    this.storage = storage;
    logger.info("GenericRepository created with storage: {}", storage);
  }

  public void save(T entity, String id) {
    logger.info("Saving entity with ID: {}", id);
    storage.save(entity, id);
    logger.info("Entity saved with ID: {}", id);
  }

  public void update(T updatedEntity, String id) {
    logger.info("Updating entity with ID: {}", id);
    storage.save(updatedEntity, id);
    logger.info("Entity updated with ID: {}", id);
  }

  public T findById(String id) {
    logger.debug("Finding entity with ID: {}", id);
    T entity = storage.findById(id);
    if (entity != null) {
      logger.info("Entity found with ID: {}", id);
    } else {
      logger.warn("Entity not found with ID: {}", id);
    }
    return entity;
  }

  public List<T> findAll() {
    logger.debug("Finding all entities");
    List<T> entities = new ArrayList<>(storage.getStorage().values());
    logger.info("Found {} entities", entities.size());
    return entities;
  }

  public void deleteById(String id) {
    logger.info("Deleting entity with ID: {}", id);
    storage.deleteById(id);
    logger.info("Entity deleted with ID: {}", id);
  }
}
