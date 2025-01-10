package task2;

import static utils.Constants.sleepForSomeTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeCollection {

  private final List<Integer> numbers = new ArrayList<>();
  private final Lock lock = new ReentrantLock();


  public void writeRandomNumbers() {
    while (true) {
      executeWithLock(() -> {
        numbers.add((int) (Math.random() * 100));
      });
      sleepForSomeTime(100);
    }
  }

  public void printSum() {
    while (true) {
      executeWithLock(() -> {
        System.out.println("Sum: " + numbers.stream().mapToInt(Integer::intValue).sum());
      });
      sleepForSomeTime(1000);
    }
  }

  public void printSqrtSumOfSquares() {
    while (true) {
      executeWithLock(() -> {
        int sum = numbers.stream().mapToInt(n -> n * n).sum();
        String message = "Square Root of Sum of Squares: ";

        if (sum < 0) {
          sum *= -1;
          System.out.println(message + Math.sqrt(sum) + "i");
        }
        else System.out.println(message + Math.sqrt(sum));

      });
      sleepForSomeTime(1000);
    }
  }


  private void executeWithLock(Runnable action) {
    lock.lock();
    try {
      action.run();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    ThreadSafeCollection collection = new ThreadSafeCollection();

    new Thread(collection::writeRandomNumbers).start();
    new Thread(collection::printSum).start();
    new Thread(collection::printSqrtSumOfSquares).start();
  }
}
