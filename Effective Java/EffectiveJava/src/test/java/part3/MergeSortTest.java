package part3;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.example.part3.MergeSort;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MergeSortTest {

  public static Stream<Arguments> provideDataForMergeSort() {
    return Stream.of(
        Arguments.of(new int[] {}, new int[] {}),  // Empty array
        Arguments.of(new int[] {1}, new int[] {1}),  // Single element
        Arguments.of(new int[] {2, 1}, new int[] {1, 2}),  // Two elements
        Arguments.of(new int[] {3, 1, 2}, new int[] {1, 2, 3}),  // Three elements
        Arguments.of(new int[] {5, 4, 3, 2, 1}, new int[] {1, 2, 3, 4, 5}),  // Reversed array
        Arguments.of(new int[] {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5}, new int[] {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9}),  // Random values
        Arguments.of(new int[] {-3, 1, -4, 1, 5, 9, -2, 6, -5, 3, 5}, new int[] {-5, -4, -3, -2, 1, 1, 3, 5, 5, 6, 9}),  // Includes negatives
        Arguments.of(new int[] {-1, 0, 1, -2, 2}, new int[] {-2, -1, 0, 1, 2})  // Mixed with zero
    );
  }

  @ParameterizedTest
  @MethodSource("provideDataForMergeSort")
  public void testMergeSort(int[] input, int[] expected) {
    MergeSort.sort(input);
    assertArrayEquals(expected, input);
    printArray(input);
  }

  private void printArray(int[] array) {
    System.out.println();
    for (int value : array) {
      System.out.print(value + " ");
    }
    System.out.println();
  }

  public static Stream<Arguments> provideDataForPartition() {
    return Stream.of(
        // Example where both subarrays have multiple elements
        Arguments.of(new int[] {3, 6, 8, 2, 4, 5}, 0, 2, 3, 5, new int[] {2, 3, 4, 5, 6, 8}),

        // Subarrays with one element each
        Arguments.of(new int[] {5, 1}, 0, 0, 1, 1, new int[] {1, 5}),

        // Subarrays with one element and the other with multiple elements
        Arguments.of(new int[] {1, 5, 7, 8, 2}, 0, 3, 4, 4, new int[] {1, 2, 5, 7, 8}),

        // All elements same
        Arguments.of(new int[] {2, 2, 2, 2}, 0, 1, 2, 3, new int[] {2, 2, 2, 2}),

        // Example with varying lengths
        Arguments.of(new int[] {1, 4, 7, 2, 5, 8}, 0, 2, 3, 5, new int[] {1, 2, 4, 5, 7, 8}),

        // Entire array already sorted naturally through input
        Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, 0, 2, 3, 5, new int[] {1, 2, 3, 4, 5, 6}),

        // Subarray at the beginning and the end with mixed middle section
        Arguments.of(new int[] {9, 10, 11, 1, 2, 3}, 0, 2, 3, 5, new int[] {1, 2, 3, 9, 10, 11})

    );
  }

  @ParameterizedTest
  @MethodSource("provideDataForPartition")
  public void testPartition(int[] array, int start1, int end1, int start2, int end2, int[] expected) {
    MergeSort.partition(array, start1, end1, start2, end2);
    assertArrayEquals(expected, array);
  }
}