package task4;

public class PoolTest {

  public static void main(String[] args) {
    BlockingObjectPool pool = new BlockingObjectPool(3);

    Runnable producer = () -> {
      try {
        for (int i = 0; i < 5; i++) {
          Object obj = new Object();

          System.out.println(Thread.currentThread().getName() + " putting object: " + obj);
          pool.take(obj);

          // Simulate work
          Thread.sleep(500);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    };

    Runnable consumer = () -> {
      try {
        for (int i = 0; i < 5; i++) {
          Object obj = pool.get();

          System.out.println(Thread.currentThread().getName() + " got object: " + obj);

          // Simulate work
          Thread.sleep(1000);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    };

    Thread producerThread = new Thread(producer, "Producer");
    Thread consumerThread = new Thread(consumer, "Consumer");

    producerThread.start();
    consumerThread.start();

    try {
      producerThread.join();
      consumerThread.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
