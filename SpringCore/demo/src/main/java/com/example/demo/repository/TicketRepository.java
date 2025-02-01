package com.example.demo.repository;

import com.example.demo.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketRepository extends GenericRepository<Ticket> {

  private static final Logger logger = LoggerFactory.getLogger(TicketRepository.class);

  public TicketRepository(Storage<Ticket> storage) {
    super(storage);
    logger.info("TicketRepository created with storage: {}", storage);
  }
}
