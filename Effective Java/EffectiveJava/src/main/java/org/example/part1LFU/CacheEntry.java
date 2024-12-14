package org.example.part1LFU;

import lombok.Data;

@Data
public class CacheEntry<T> {
  private T value;
  private long lastAccessTime;

  public CacheEntry(T value) {
    this.value = value;
  }
}