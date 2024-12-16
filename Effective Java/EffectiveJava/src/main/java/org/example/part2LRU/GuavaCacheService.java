package org.example.part2LRU;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.concurrent.TimeUnit;
import org.example.part1LFU.CacheEntry;  // Ensure this class is correctly imported and generic

public class GuavaCacheService<T> {
  private final Cache<String, CacheEntry<T>> cache;
  private long totalPutTime = 0;
  private int totalPuts = 0;
  private int evictionCount = 0;

  public GuavaCacheService(RemovalListener<String, CacheEntry<T>> customRemovalListener) {
    this.cache = CacheBuilder.newBuilder()
        .expireAfterAccess(5, TimeUnit.SECONDS)
        .removalListener(customRemovalListener)
        .maximumSize(100000)
        .build();
  }

  public synchronized void put(String key, CacheEntry<T> value) {
    long startTime = System.currentTimeMillis();
    cache.put(key, value);
    long endTime = System.currentTimeMillis();
    totalPutTime += endTime - startTime;
    totalPuts++;
  }

  public CacheEntry<T> get(String key) {
    return cache.getIfPresent(key);
  }

  public double getAveragePutTime() {
    return totalPuts == 0 ? 0 : (double) totalPutTime / totalPuts;
  }

  public int getNumberOfCacheEvictions() {
    return evictionCount;
  }
}