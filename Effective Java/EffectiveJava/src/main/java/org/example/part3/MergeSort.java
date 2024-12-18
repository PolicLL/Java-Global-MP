package org.example.part3;

public class MergeSort {

  public static void sort(int[] array) {
    sort(array, 0, array.length - 1);
  }

  private static void sort(int[] array, int start, int end) {
    if(start < end) {
      int middle = (start + end) / 2;
      sort(array, start, middle);
      sort(array, middle + 1, end);
      partition(array, start, middle, middle + 1, end);
    }
  }

  // 9, 10, 11, 1, 2, 3
  // 0   1   2  3  4  5

  public static void partition(int[] array, int start1, int end1, int start2, int end2) {
    // setup 1
    int[] array1 = new int[end1 - start1 + 2];
    int mainIndex = start1;

    for(int i = 0; i < array1.length - 1; ++i)
      array1[i] = array[mainIndex++];

    array1[array1.length - 1] = Integer.MAX_VALUE;

    // setup 2
    int[] array2 = new int[end2 - start2 + 2];

    for(int i = 0; i < array2.length - 1; ++i)
      array2[i] = array[mainIndex++];

    array2[array2.length - 1] = Integer.MAX_VALUE;

    // start
    int index1 = 0, index2 = 0;
    mainIndex = start1;

    while(index1 != (array1.length - 1) || index2 != (array2.length - 1)) {
      if(array1[index1] < array2[index2]) {
        array[mainIndex++] = array1[index1++];
      }
      else
        array[mainIndex++] = array2[index2++];
    }

  }

}
