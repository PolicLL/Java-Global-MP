package org.task1;

import java.util.HashMap;
import java.util.Map;

public class ThreadSafeMap<K, V> {

  private final Map<K, V> map = new HashMap<>();

  public synchronized void put(K key, V value) {
    map.put(key, value);
  }

  public synchronized void get(K key) {
    map.get(key);
  }

  public synchronized int size() {
    return map.size();
  }

  public synchronized int sumValues() {
    return map.values().stream()
        .filter(value -> value instanceof Number)
        .mapToInt(value -> ((Number) value).intValue())
        .sum();
  }

}
