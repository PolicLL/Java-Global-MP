package org.example.part2LRU;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import java.util.concurrent.TimeUnit;
import org.example.part1LFU.CacheEntry;
import org.example.util.CacheStatistics;

public class GuavaCacheService<T> {
  private final Cache<String, CacheEntry<T>> cache;

  private final CacheStatistics statistics;


  public GuavaCacheService(RemovalListener<String, CacheEntry<T>> customRemovalListener) {
    this.statistics = new CacheStatistics();

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
    statistics.addPutTime(endTime - startTime);
  }

  public CacheEntry<T> get(String key) {
    return cache.getIfPresent(key);
  }

  public void printStatistics() {
    System.out.println("Average put time: " + statistics.getAveragePutTime() + " ms");
    System.out.println("Number of evictions: " + statistics.getNumberOfCacheEvictions());
  }
}