package com.example.demo.dto;


import lombok.Builder;

@Builder
public record TicketDto(String id, String name, String email) {}