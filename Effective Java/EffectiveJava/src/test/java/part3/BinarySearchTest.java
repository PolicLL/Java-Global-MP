package part3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.example.part3.BinarySearch;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class BinarySearchTest {

  public static Stream<Arguments> provideDataForBinarySearch() {
    return Stream.of(
        Arguments.of(new int[] {}, 5, -1),  // Empty array
        Arguments.of(new int[] {10}, 10, 0),  // Single element, target found
        Arguments.of(new int[] {10}, 5, -1),  // Single element, target not found
        Arguments.of(new int[] {10, 20}, 20, 1),  // Two elements, target is the last
        Arguments.of(new int[] {10, 20}, 15, -1),  // Two elements, target not found
        Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 4, 3),  // Multiple elements, target in the middle
        Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 8, 7),  // Multiple elements, target in the end
        Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 1, 0),  // Multiple elements, target in the beginning
        Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10, 9)  // Larger array, target last
    );
  }

  @ParameterizedTest
  @MethodSource("provideDataForBinarySearch")
  public void testBinarySearchIteratively(int[] array, int target, int expectedIndex) {
    assertEquals(expectedIndex, BinarySearch.binarySearchIteratively(array, target));
  }

  @ParameterizedTest
  @MethodSource("provideDataForBinarySearch")
  public void testBinarySearchRecursively(int[] array, int target, int expectedIndex) {
    assertEquals(expectedIndex, BinarySearch.binarySearchRecursively(array, target));
  }

}
