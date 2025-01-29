package com.example.demo.repository;

import com.example.demo.model.Ticket;

public class TicketRepository extends GenericRepository<Ticket> {

  public TicketRepository(Storage<Ticket> storage) {
    super(storage);
  }
}
