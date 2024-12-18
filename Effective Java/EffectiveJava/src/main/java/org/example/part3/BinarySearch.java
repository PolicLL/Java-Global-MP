package org.example.part3;

import org.example.part3.sort.SortStrategy;

public class BinarySearch {

  public static int binarySearchUnsortedArray(int[] array, int target, SortStrategy sortStrategy) {
    sortStrategy.sort(array);
    return binarySearchIteratively(array, target);
  }

  public static int binarySearchIteratively(int[] array, int target) {
    int low = 0, high = array.length - 1;

    while(low <= high) {
      int middle = (low + high) / 2;
      if(array[middle] == target) return middle;
      else if(array[middle] > target) high = middle - 1;
      else low = middle + 1;
    }

    return -1;
  }

  public static int binarySearchRecursively(int[] array, int target) {
    return binarySearchRecursively(array, target, 0, array.length - 1);
  }

  private static int binarySearchRecursively(int[] array, int target, int low, int high) {
    if(low > high) return -1;

    int middle = (low + high) / 2;

    if(array[middle] == target) return middle;
    else if(array[middle] > target) return binarySearchRecursively(array, target, low, middle - 1);
    else return binarySearchRecursively(array, target, middle + 1, high);
  }

}
