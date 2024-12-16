package org.example;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.Random;
import org.example.part1LFU.CacheEntry;
import org.example.part1LFU.CacheService;
import org.example.part2LRU.GuavaCacheService;

public class Main {
  public static void main2(String[] args) throws InterruptedException {

    int LIMIT = 50;

    CacheService<Integer> cacheService = new CacheService<>(LIMIT / 10, (key, value) ->
        System.out.println("Listener: Removed entry - Key: " + key + ", Value: " + value));

    for (int i = 0; i < LIMIT; i++) {
      if(new Random().nextInt(10) % 2 == 0) {
        cacheService.put("key" + i, new CacheEntry<>(new Random().nextInt(100)));
      }
      else {
        cacheService.get("key" + new Random().nextInt(LIMIT));
      }
      Thread.sleep(1);
    }

    //Thread.sleep(LIMIT * 10);

    cacheService.printStatistics();
  }

  public static void main(String[] args) throws InterruptedException {
    RemovalListener<String, CacheEntry<String>> myListener = new RemovalListener<>() {
      @Override
      public void onRemoval(RemovalNotification<String, CacheEntry<String>> notification) {
        System.out.println("Custom removal logic for key: " + notification.getKey());
        if (notification.wasEvicted()) {
          System.out.println("Reason: " + notification.getCause());
        }
      }
    };

    GuavaCacheService<String> myService = new GuavaCacheService<>(myListener);
  }
}