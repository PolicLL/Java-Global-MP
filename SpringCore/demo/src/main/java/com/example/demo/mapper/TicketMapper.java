package com.example.demo.mapper;

import com.example.demo.dto.TicketDto;
import com.example.demo.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {
  TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

  @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
  TicketDto toDto(Ticket ticket);
  Ticket toModel(TicketDto ticketDto);
}
