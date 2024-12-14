package part1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.example.part1LFU.CacheEntry;
import org.example.part1LFU.CacheService;
import org.example.part1LFU.RemovalListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CacheServiceTest {

  private CacheService<String> cacheService;

  @BeforeEach
  void setUp() {
    cacheService = new CacheService<>(3, new RemovalListenerTest<>());
  }

  @Test
  void testPutAndGet() {
    // when
    cacheService.put("key1", new CacheEntry<>("value1"));

    // then
    CacheEntry<String> retrievedEntry = cacheService.get("key1");
    assertNotNull(retrievedEntry);
    assertEquals("value1", retrievedEntry.getValue());
  }

  @Test
  void testEviction() {
    // given
    cacheService.put("key1", new CacheEntry<>("value1"));
    cacheService.put("key2", new CacheEntry<>("value2"));
    cacheService.put("key3", new CacheEntry<>("value3"));

    cacheService.get("key1");
    cacheService.get("key1");
    cacheService.get("key1");
    cacheService.get("key3");
    cacheService.get("key3");

    // when
    cacheService.put("key4", new CacheEntry<>("value4"));

    // then
    assertNull(cacheService.get("key2"));

    CacheEntry<String> retrievedEntry = cacheService.get("key4");
    assertNotNull(retrievedEntry);
    assertEquals("value4", retrievedEntry.getValue());

  }

  @Test
  void testEvictionByTime() throws InterruptedException {
    // when
    cacheService.put("key1", new CacheEntry<>("value1"));
    Thread.sleep(6000);

    // then
    assertNull(cacheService.get("key1"));
  }

  @Test
  void testRemovalListener() {
    // given
    RemovalListenerTest<String> listener = new RemovalListenerTest<>();
    CacheService<String> cacheServiceWithListener = new CacheService<>(10, listener);

    CacheEntry<String> cacheEntry = new CacheEntry<>("value1");

    // when
    cacheServiceWithListener.put("key1", cacheEntry);
    cacheServiceWithListener.remove("key1");

    // then
    assertFalse(listener.removedEntries.isEmpty());
    assertTrue(listener.removedEntries.contains(cacheEntry));
  }

  static class RemovalListenerTest<T> implements RemovalListener<T> {
    String removedKey;
    List<CacheEntry<T>> removedEntries = new ArrayList<>();

    @Override
    public void onRemoval(String key, CacheEntry<T> removedEntry) {
      this.removedKey = key;
      removedEntries.add(removedEntry);
    }
  }
}