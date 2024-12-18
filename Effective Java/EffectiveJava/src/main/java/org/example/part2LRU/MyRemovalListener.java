package org.example.part2LRU;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import lombok.RequiredArgsConstructor;
import org.example.part1LFU.CacheEntry;
import org.example.util.CacheStatistics;

@RequiredArgsConstructor
public class MyRemovalListener<T> implements RemovalListener<String, CacheEntry<T>> {

  private final CacheStatistics statistics;
  private final RemovalListener<String, CacheEntry<T>> userListener;

  @Override
  public void onRemoval(RemovalNotification<String, CacheEntry<T>> notification) {
    if (notification.wasEvicted()) {
      // 8. Number of cache evictions
      statistics.incrementEvictions();
      assert notification.getValue() != null;
      // 5. Just add to log of removed entry
      System.out.println("Evicted: " + notification.getKey() + ":" + notification.getValue().getValue()
          + ", cause: " + notification.getCause());
    }

    if (userListener != null) {
      userListener.onRemoval(notification);
    }
  }
}