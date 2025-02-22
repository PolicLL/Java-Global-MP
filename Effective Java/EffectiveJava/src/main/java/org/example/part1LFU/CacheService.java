package org.example.part1LFU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.example.util.CacheStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple Java (60 points) (Strategy: LFU)
 *
 * @param <T>
 */
public class CacheService<T> {

  private static final Logger log = LoggerFactory.getLogger(CacheService.class);

  // 1. Max Size = 100 000
  private final int MAX_SIZE;

  // 9. Support concurrency
  private final ConcurrentHashMap<String, CacheEntry<T>> cache;
  private final ConcurrentHashMap<String, Integer> accessCount;

  private final CacheStatistics statistics;
  private final CustomRemovalListener<T> customRemovalListener;

  private final ScheduledExecutorService executorService;
  private ScheduledFuture<?> scheduledFuture;

  public CacheService(int maxSize, CustomRemovalListener<T> customRemovalListener) {
    this.cache = new ConcurrentHashMap<>();
    this.accessCount = new ConcurrentHashMap<>();
    this.customRemovalListener = customRemovalListener;
    this.statistics = new CacheStatistics();
    this.executorService = Executors.newSingleThreadScheduledExecutor();

    if(maxSize >= 0) this.MAX_SIZE = maxSize;
    else this.MAX_SIZE = 100000;

    scheduleTimedEviction();
  }


  /**
   * 3. Time-based on last access (5 seconds)
   *
   * Method that runs every second and removes elements that are not accessed in last 5 seconds.
   */
  private void scheduleTimedEviction() {
    scheduledFuture = executorService.scheduleAtFixedRate(() -> {
      long currentTime = System.currentTimeMillis();
      List<String> keysToRemove = new ArrayList<>();

      for (Map.Entry<String, CacheEntry<T>> entry : cache.entrySet()) {
        if (currentTime - entry.getValue().getLastAccessTime() >= 5000) {
          keysToRemove.add(entry.getKey());
        }
      }

      for (String key : keysToRemove) {
        remove(key);
      }

      stopIfCacheEmpty();

    }, 0, 1, TimeUnit.SECONDS);
  }

  private void stopIfCacheEmpty() {
    if (cache.isEmpty()) {
      scheduledFuture.cancel(false);
      executorService.shutdown();
    }
  }

  public CacheEntry<T> get(String key) {
    CacheEntry<T> entry = cache.get(key);
    if (entry != null) {
      accessCount.put(key, accessCount.getOrDefault(key, 0) + 1);
      entry.setLastAccessTime(System.currentTimeMillis());

      log.info("Getting for " + key + " value : " + entry.getValue());
    }

    return entry;
  }

  public synchronized void put(String key, CacheEntry<T> value) {
    long startTime = System.currentTimeMillis();
    if (cache.size() >= MAX_SIZE) {
      evict();
    }

    value.setLastAccessTime(System.currentTimeMillis());
    cache.put(key, value);
    accessCount.put(key, 1);
    statistics.addPutTime(System.currentTimeMillis() - startTime);

    log.info("Put value : " + value);
  }


  // 2. Eviction policy
  private void evict() {
    if (!cache.isEmpty()) {
      String leastFrequentlyUsedKey = Collections.min(accessCount.entrySet(),
          Comparator.comparingInt(Map.Entry::getValue)).getKey();
      remove(leastFrequentlyUsedKey);
      statistics.incrementEvictions();
    }
  }

  public void remove(String key) {
    CacheEntry<T> removedEntry = cache.remove(key);
    accessCount.remove(key);

    // 5. Just add to log of removed entry
    log.info("Removed key: " + key);

    // 4. Removal listener
    if (customRemovalListener != null && removedEntry != null) {
      customRemovalListener.onRemoval(key, removedEntry);
    }
  }

  // 6. Give statistic to user
  public void printStatistics() {
    System.out.println("Average put time: " + statistics.getAveragePutTime() + " ms");
    System.out.println("Number of evictions: " + statistics.getNumberOfCacheEvictions());
  }

}
