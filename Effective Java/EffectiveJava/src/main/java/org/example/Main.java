package org.example;

import java.util.Random;
import org.example.part1LFU.CacheEntry;
import org.example.part1LFU.CacheService;

public class Main {
  public static void main(String[] args) throws InterruptedException {

    int LIMIT = 5000;

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
}