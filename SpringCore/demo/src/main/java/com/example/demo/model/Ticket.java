package com.example.demo.model;

import lombok.Builder;

@Builder
public record Ticket(String id, String userId, String eventId, int seatNumber) {}

