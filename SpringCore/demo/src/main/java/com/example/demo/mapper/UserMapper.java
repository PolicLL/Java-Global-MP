package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import java.util.UUID;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @BeforeMapping
  default void setId(
      @MappingTarget User.UserBuilder userBuilder) {
    userBuilder.id(UUID.randomUUID().toString());
  }

  UserDto toDto(User user);

  @Mapping(target = "id", ignore = true)
  User toModel(UserDto userDto);
}
