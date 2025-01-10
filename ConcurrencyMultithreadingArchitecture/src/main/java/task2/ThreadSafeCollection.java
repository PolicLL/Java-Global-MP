package task2;

import static utils.Constants.sleepForSomeTime;

import java.util.ArrayList;
import java.util.List;

public class ThreadSafeCollection {

  private final List<Integer> numbers = new ArrayList<>();


  public void writeRandomNumbers() {
    while (true) {
      synchronized (this) {  // Synchronize block to ensure thread safety
        numbers.add((int) (Math.random() * 100));
      }
      sleepForSomeTime(100);
    }
  }

  public void printSum() {
    while (true) {
      synchronized (this) {  // Synchronize block to ensure thread safety
        System.out.println("Sum: " + numbers.stream().mapToInt(Integer::intValue).sum());
      }
      sleepForSomeTime(1000);
    }
  }

  public void printSqrtSumOfSquares() {
    while (true) {
      synchronized (this) {  // Synchronize block to ensure thread safety
        int sum = numbers.stream().mapToInt(n -> n * n).sum();
        String message = "Square Root of Sum of Squares: ";

        if (sum < 0) {
          sum *= -1;
          System.out.println(message + Math.sqrt(sum) + "i");
        } else {
          System.out.println(message + Math.sqrt(sum));
        }
      }
      sleepForSomeTime(1000);
    }
  }

  public static void main(String[] args) {
    ThreadSafeCollection collection = new ThreadSafeCollection();

    new Thread(collection::writeRandomNumbers).start();
    new Thread(collection::printSum).start();
    new Thread(collection::printSqrtSumOfSquares).start();
  }
}
