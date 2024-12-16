package org.example.util;

public class CacheStatistics {
  private long totalPutTime = 0;
  private int totalPuts = 0;

  // 8. Number of cache evictions
  private int numberOfCacheEvictions = 0;

  public synchronized void addPutTime(long time) {
    totalPutTime += time;
    totalPuts++;
  }

  public synchronized void incrementEvictions() {
    numberOfCacheEvictions++;
  }

  /**
   * 7. Average time spent for putting new values into cache
   *
   * @return average time spent for insertion of new value based on values which
   * are tracked in put() method.
   */
  public synchronized double getAveragePutTime() {
    return totalPuts == 0 ? 0 : (double) totalPutTime / totalPuts;
  }

  public synchronized int getNumberOfCacheEvictions() {
    return numberOfCacheEvictions;
  }
}