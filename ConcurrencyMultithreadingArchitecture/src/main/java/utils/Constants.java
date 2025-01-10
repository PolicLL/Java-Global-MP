package utils;

import java.util.Random;

public class Constants {

  public static Random RANDOM = new Random();;

  public static void sleepForSomeTime(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

}
