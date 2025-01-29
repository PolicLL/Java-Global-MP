package com.example.demo.mapper;

import com.example.demo.config.GlobalMapperConfig;
import com.example.demo.dto.EventDto;
import com.example.demo.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = GlobalMapperConfig.class)
public interface EventMapper {
  EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

  @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
  EventDto toDto(Event event);
  Event toModel(EventDto eventDto);
}
