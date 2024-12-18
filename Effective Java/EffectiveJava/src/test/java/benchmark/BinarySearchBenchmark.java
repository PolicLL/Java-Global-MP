package benchmark;



import java.util.Random;
import org.example.part3.BinarySearch;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BinarySearchBenchmark {

  @Param({"100", "1000", "10000"})
  private int arraySize;

  private int[] array;
  private int target;

  @Setup
  public void setup() {
    array = new int[arraySize];
    for (int i = 0; i < arraySize; i++) {
      array[i] = i * 2;
    }
    target = new Random().nextInt(arraySize) * 2; // Ensuring the target exists
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  public void benchmarkBinarySearchIteratively() {
    BinarySearch.binarySearchIteratively(array, target);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  public void benchmarkBinarySearchRecursively() {
    BinarySearch.binarySearchRecursively(array, target);
  }
}