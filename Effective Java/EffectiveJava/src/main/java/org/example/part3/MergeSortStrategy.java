package org.example.part3;

public class MergeSortStrategy implements SortStrategy {
  @Override
  public void sort(int[] array) {
    MergeSort.sort(array); // Reference to your previous MergeSort implementation
  }
}