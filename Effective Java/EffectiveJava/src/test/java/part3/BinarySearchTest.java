package part3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.example.part3.BinarySearch;
import org.example.part3.InsertionSortStrategy;
import org.example.part3.MergeSortStrategy;
import org.example.part3.SortStrategy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class BinarySearchTest {

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
  @MethodSource("provideSearchData")
  public void testBinarySearch(int[] input, int target, SortStrategy sortStrategy, int expectedIndex) {
    int index = BinarySearch.binarySearchUnsortedArray(input, target, sortStrategy);
    assertEquals(expectedIndex, index);
  }

  private static Stream<Arguments> provideSearchData() {
    return Stream.of(
        // Existing Test Cases
        Arguments.of(new int[] {3, 5, 1, 8}, 5, new InsertionSortStrategy(), 2),
        Arguments.of(new int[] {3, 5, 1, 8}, 5, new MergeSortStrategy(), 2),
        Arguments.of(new int[] {3, 5, -2, 8}, -2, new InsertionSortStrategy(), 0),
        Arguments.of(new int[] {3, 5, -2, 8}, -2, new MergeSortStrategy(), 0),

        // New Longer Test Cases
        Arguments.of(new int[] {20, 11, 25, 1, 3, 6, 19, 4, 21, 15}, 21, new InsertionSortStrategy(), 8),
        Arguments.of(new int[] {20, 11, 25, 1, 3, 6, 19, 4, 21, 15}, 21, new MergeSortStrategy(), 8),
        Arguments.of(new int[] {31, 12, 16, 9, 8, 27, -5, 18}, -5, new InsertionSortStrategy(), 0),
        Arguments.of(new int[] {31, 12, 16, 9, 8, 27, -5, 18}, -5, new MergeSortStrategy(), 0),
        Arguments.of(new int[] {45, 56, 12, 78, 3, -4, -89, 24, 65, 32, 120}, -89, new MergeSortStrategy(), 0),
        Arguments.of(new int[] {45, 56, 12, 78, 3, -4, -89, 24, 65, 32, 120}, 120, new MergeSortStrategy(), 10),
        Arguments.of(new int[] {45, 56, 12, 78, 3, -4, -89, 24, 65, 32, 120}, 24, new InsertionSortStrategy(), 4)
    );
  }
}
