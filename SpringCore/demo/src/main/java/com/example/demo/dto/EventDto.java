package com.example.demo.dto;

import lombok.Builder;

@Builder
public record EventDto(String id, String title, String date) {}