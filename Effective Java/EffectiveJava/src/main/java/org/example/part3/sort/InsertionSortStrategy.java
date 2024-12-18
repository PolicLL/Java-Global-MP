package org.example.part3.sort;

public class InsertionSortStrategy implements SortStrategy {
  @Override
  public void sort(int[] array) {
    InsertionSort.sort(array);
  }
}
