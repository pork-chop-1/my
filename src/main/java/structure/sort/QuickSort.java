package structure.sort;

import java.util.Arrays;

public class QuickSort {
  public static void main(String[] args) {
    int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0, 123, -1,-23,1234,-123,1 };
    System.out.println(Arrays.toString(arr));
    quickSort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));
  }

  public static void quickSort(int[] arr, int left, int right) {
    int l = left;
    int r = right;
    int value = arr[(r + l) / 2];

    int temp = 0;

    while (l < r) {
      while (arr[l] < value) {
        l++;
      }
      while (arr[r] > value) {
        r--;
      }
      if (l == r) {
        break;
      }
      // System.out.println(Arrays.toString(arr));
      temp = arr[l];
      arr[l] = arr[r];
      arr[r] = temp;

      if (arr[l] == value) {
        r--;
      }
      if (arr[r] == value) {
        l++;
      }
      // System.out.println(Arrays.toString(arr));
    }
    if (left < r - 1)
      quickSort(arr, left, r - 1);
    if (right > r + 1)
      quickSort(arr, r + 1, right);

  }
}
