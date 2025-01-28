package com.example.demo.model;

import lombok.Builder;

@Builder
public record Event(String id, String title, String date) {}

