package com.example.demo.dto;

import lombok.Builder;

@Builder
public record UserDto(String id, String name, String email) {}
