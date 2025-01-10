# Task 1

## HashMap (Baseline)

- Not thread-safe.
- Throws ConcurrentModificationException when modified during iteration.
- Fast in single-threaded applications because there's no synchronization overhead.
- Unsuitable for multithreaded scenarios due to lack of thread safety.

## Collections.synchronizedMap()

- Ensures all access to the underlying map is synchronized.
- Thread-safe but locks the entire map for every operation (coarse-grained locking).
- Uses single lock.

## ConcurrentHashMap

- Thread-safe using a fine-grained locking mechanism.
- Allows concurrent reads and isolated writes on different buckets.
- only parts of the map are locked for writes.
- Supports methods like compute, merge, and forEach designed for safe concurrent updates.0

## Custom Thread-Safe Map with Synchronization

- Custom implementation using synchronized methods.
- All map operations are synchronized.
- Uses single lock.

