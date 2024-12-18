package part3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.example.part3.sort.InsertionSort;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class InsertionSortTest {

  public static Stream<Arguments> provideDataForInsertionSort() {
    return Stream.of(
        Arguments.of(new int[] {}, new int[] {}),  // Empty array
        Arguments.of(new int[] {1}, new int[] {1}),  // Single element
        Arguments.of(new int[] {2, 1}, new int[] {1, 2}),  // Two elements, reverse order
        Arguments.of(new int[] {4, 3, 2, 1}, new int[] {1, 2, 3, 4}),  // Multiple elements, reverse order
        Arguments.of(new int[] {1, 2, 3, 4}, new int[] {1, 2, 3, 4}),  // Already sorted
        Arguments.of(new int[] {3, 1, 4, 1, 5, 9, 2, 6, 5}, new int[] {1, 1, 2, 3, 4, 5, 5, 6, 9}),  // Unsorted with duplicates
        Arguments.of(new int[] {-3, -1, -4, 0, 2}, new int[] {-4, -3, -1, 0, 2})  // Mixed negative and positive
    );
  }

  @ParameterizedTest
  @MethodSource("provideDataForInsertionSort")
  public void testInsertionSort(int[] input, int[] expected) {
    InsertionSort.sort(input);
    assertArrayEquals(expected, input, "The sorted arrays do not match!");
  }
}