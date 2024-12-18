package part2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.ArrayList;
import java.util.List;
import org.example.part1LFU.CacheEntry;
import org.example.part1LFU.CacheService;
import org.example.part1LFU.CustomRemovalListener;
import org.example.part2LRU.GuavaCacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GuavaCacheServiceTest {

  private GuavaCacheService<String> guavaCacheService;


  @BeforeEach
  void setUp() {
    guavaCacheService = new GuavaCacheService<>(3, new RemovalListenerTest<>());
  }

  @Test
  void testPutAndGet() {
    // when
    guavaCacheService.put("key1", new CacheEntry<>("value1"));

    // then
    CacheEntry<String> retrievedEntry = guavaCacheService.get("key1");
    assertNotNull(retrievedEntry);
    assertEquals("value1", retrievedEntry.getValue());
  }

  @Test
  void testEviction() {
    // given
    guavaCacheService.put("key1", new CacheEntry<>("value1"));
    guavaCacheService.put("key2", new CacheEntry<>("value2"));
    guavaCacheService.put("key3", new CacheEntry<>("value3"));

    guavaCacheService.get("key1");
    guavaCacheService.get("key2");
    guavaCacheService.get("key3");

    // when
    guavaCacheService.put("key4", new CacheEntry<>("value4"));

    // then
    assertNull(guavaCacheService.get("key1"));

    CacheEntry<String> retrievedEntry = guavaCacheService.get("key4");
    assertNotNull(retrievedEntry);
    assertEquals("value4", retrievedEntry.getValue());

  }

  @Test
  void testEvictionByTime() throws InterruptedException {
    // when
    guavaCacheService.put("key1", new CacheEntry<>("value1"));
    Thread.sleep(6000);

    // then
    assertNull(guavaCacheService.get("key1"));
  }

  @Test
  void testRemovalListener() {
    // given
    RemovalListenerTest<String> listener = new RemovalListenerTest<>();
    GuavaCacheService<String> cacheServiceWithListener = new GuavaCacheService<>(10, listener);

    CacheEntry<String> cacheEntry = new CacheEntry<>("value1");

    // when
    cacheServiceWithListener.put("key1", cacheEntry);
    cacheServiceWithListener.remove("key1");

    // then
    assertFalse(listener.removedEntries.isEmpty());
    assertTrue(listener.removedEntries.contains(cacheEntry));
  }

  static class RemovalListenerTest<String> implements RemovalListener<String, CacheEntry<String>> {
    List<CacheEntry<String>> removedEntries = new ArrayList<>();

    @Override
    public void onRemoval(RemovalNotification<String, CacheEntry<String>> notification) {
      removedEntries.add(notification.getValue());
    }
  }
}