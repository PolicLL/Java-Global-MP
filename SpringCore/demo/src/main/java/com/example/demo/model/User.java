package com.example.demo.model;

import lombok.Builder;

@Builder
public record User(String id, String name, String email) {}
