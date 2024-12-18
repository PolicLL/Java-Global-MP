package benchmark;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.example.part3.BinarySearch;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.annotations.Measurement;

@State(Scope.Thread)
public class BinarySearchBenchmark {

  @Param({"100", "1000", "10000"})
  private int arraySize;

  private int[] array;
  private int target;

  private static final long SEED = 12345L;
  private final Random random = new Random(SEED);

  @Setup
  public void setup() {
    array = new int[arraySize];
    for (int i = 0; i < arraySize; i++) {
      array[i] = i * 2;
    }
    target = random.nextInt(arraySize) * 2; // Ensuring the target exists
  }

  @Benchmark
  @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
  @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
  @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
  public void benchmarkBinarySearchIteratively() {
    BinarySearch.binarySearchIteratively(array, target);
  }

  @Benchmark
  @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
  @Warmup(iterations = 5, time = 1)
  @Measurement(iterations = 5, time = 1)
  public void benchmarkBinarySearchRecursively() {
    int result = BinarySearch.binarySearchRecursively(array, target);
  }
}