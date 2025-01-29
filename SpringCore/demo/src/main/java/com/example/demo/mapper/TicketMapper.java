package com.example.demo.mapper;

import com.example.demo.dto.TicketDto;
import com.example.demo.model.Ticket;
import java.util.UUID;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

public interface TicketMapper {
  TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

  @BeforeMapping
  default void setId(
      @MappingTarget Ticket.TicketBuilder ticketBuilder) {
    ticketBuilder.id(UUID.randomUUID().toString());
  }

  TicketDto toDto(Ticket ticket);

  @Mapping(target = "id", ignore = true)
  Ticket toModel(TicketDto ticketDto);
}
