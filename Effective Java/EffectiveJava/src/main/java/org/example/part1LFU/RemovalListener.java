package org.example.part1LFU;

/**
 * 4. Removal listener
 *
 * Should be called when element is removed from
 */
@FunctionalInterface
public interface RemovalListener<T> {
  void onRemoval(String key, CacheEntry<T> value);
}
