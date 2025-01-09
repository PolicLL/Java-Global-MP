package org.task1;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Task1 {

  public static void main(String[] args) {


    /* APPROACH 1

    Approach that will cause ConcurrentModificationException to be thrown.
    I managed to handle this exception in catch block, and after it is catched, program finishes.

    */
    // defaultApproach(new HashMap<>(), 1000, 20);


    /* APPROACH 2

    Approach that uses ConcurrentHashMap.

    */
    // defaultApproach(new ConcurrentHashMap<>(), 100, 20);

    /* APPROACH 3

    Using customized map.

    */
    customApproach(100, 20);



  }

  public static void defaultApproach(Map<Integer, Integer> providedMap, int VALUE, int ms) {

    final Map<Integer, Integer> map = providedMap;

    Thread writerThread = new Thread(() -> {
      for (int i = 1; i <= VALUE; i++) {
          map.put(i, i * 10);
          System.out.println("Added: " + i + " -> " + (i * 10));
          sleepForSomeTime(ms);
      }
    });

    Thread readerThread = new Thread(() -> {
      do {
        try{
          int sum = map.values().stream().mapToInt(Integer::intValue).sum();
          System.out.println("Current sum: " + sum);
          sleepForSomeTime(ms);
        }
        catch (ConcurrentModificationException e) {
          System.out.println("ConcurrentModificationException thrown.");
          System.out.println("Stopping the program.");
          System.exit(0);
        }
      } while (map.size() != VALUE);
    });

    writerThread.start();
    readerThread.start();

    try {
      writerThread.join();
      readerThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Final Map: " + map);
  }

  public static void customApproach(int VALUE, int ms) {

    final ThreadSafeMap<Integer, Integer> map = new ThreadSafeMap<>();

    Thread writerThread = new Thread(() -> {
      for (int i = 1; i <= VALUE; i++) {
        map.put(i, i * 10);
        System.out.println("Added: " + i + " -> " + (i * 10));
        sleepForSomeTime(ms);
      }
    });

    Thread readerThread = new Thread(() -> {
      do {
        try{
          System.out.println("Current sum: " + map.sumValues());
          sleepForSomeTime(ms);
        }
        catch (ConcurrentModificationException e) {
          System.out.println("ConcurrentModificationException thrown.");
          System.out.println("Stopping the program.");
          System.exit(0);
        }
      } while (map.size() != VALUE);
    });

    writerThread.start();
    readerThread.start();

    try {
      writerThread.join();
      readerThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Final Map: " + map);
  }

  private static void sleepForSomeTime(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }
}
