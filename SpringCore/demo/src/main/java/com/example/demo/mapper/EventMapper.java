package com.example.demo.mapper;

import com.example.demo.dto.EventDto;
import com.example.demo.model.Event;
import java.util.UUID;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
  EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

  @BeforeMapping
  default void setId(
      @MappingTarget Event.EventBuilder eventBuilder) {
    eventBuilder.id(UUID.randomUUID().toString());
  }

  EventDto toDto(Event event);

  @Mapping(target = "id", ignore = true)
  Event toModel(EventDto eventDto);
}
