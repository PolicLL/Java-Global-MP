package org.example.part3.sort;

public class MergeSortStrategy implements SortStrategy {
  @Override
  public void sort(int[] array) {
    MergeSort.sort(array);
  }
}