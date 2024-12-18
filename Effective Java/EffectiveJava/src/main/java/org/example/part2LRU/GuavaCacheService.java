package org.example.part2LRU;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import java.util.concurrent.TimeUnit;
import org.example.part1LFU.CacheEntry;
import org.example.util.CacheStatistics;

public class GuavaCacheService<T> {

  // 2. Eviction policy
  // 9. Support concurrency
  private final Cache<String, CacheEntry<T>> cache;
  private final CacheStatistics statistics;

  // 1. Max Size = 100 000
  private final int MAX_SIZE;

  public GuavaCacheService(int maxSize, RemovalListener<String, CacheEntry<T>> customRemovalListener) {
    this.statistics = new CacheStatistics();

    if(maxSize >= 0) this.MAX_SIZE = maxSize;
    else this.MAX_SIZE = 100000;

    this.cache = CacheBuilder.newBuilder()
        .expireAfterAccess(5, TimeUnit.SECONDS) // 3. Time-based on last access (5 seconds)
        // 4. Removal listener
        .removalListener(new MyRemovalListener<>(statistics, customRemovalListener))
        .maximumSize(MAX_SIZE)
        .build();
  }

  public synchronized void put(String key, CacheEntry<T> value) {
    long startTime = System.currentTimeMillis();
    cache.put(key, value);
    long endTime = System.currentTimeMillis();
    statistics.addPutTime(endTime - startTime);
  }

  public CacheEntry<T> get(String key) {
    return cache.getIfPresent(key);
  }

  public void remove(String key) {
    cache.invalidate(key);
  }


  // 6. Give statistic to user
  public void printStatistics() {
    System.out.println("Average put time: " + statistics.getAveragePutTime() + " ms");
    System.out.println("Number of evictions: " + statistics.getNumberOfCacheEvictions());
  }
}