package org.example.part3;

public class InsertionSortStrategy implements SortStrategy {
  @Override
  public void sort(int[] array) {
    InsertionSort.sort(array); // Reference to your previous InsertionSort implementation
  }
}
