package task4;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingObjectPool {

  private final Queue<Object> pool;
  private final int MAX_SIZE;

  /**
   * Creates a filled pool of the passed size.
   *
   * @param size of the pool
   */
  public BlockingObjectPool(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("Size must be greater than 0");
    }

    this.pool = new LinkedList<>();
    this.MAX_SIZE = size;

    for (int i = 0; i < size; i++) {
      pool.add(new Object());
    }
  }

  /**
   * Gets an object from the pool or blocks if the pool is empty.
   *
   * @return object from the pool
   */
  public synchronized Object get() {
    while (pool.isEmpty()) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt(); // Restore interrupt status
        throw new RuntimeException("Thread interrupted while waiting to get an object", e);
      }
    }

    Object obj = pool.poll();

    notifyAll();

    return obj;
  }

  /**
   * Puts an object back into the pool or blocks if the pool is full.
   *
   * @param object to be returned to the pool
   */
  public synchronized void take(Object object) {
    while (pool.size() >= MAX_SIZE) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Thread interrupted while waiting to take an object", e);
      }
    }

    pool.add(object);

    notifyAll();
  }
}
