package structure.sort;

import java.util.Arrays;

public class MergeSort {
  public static void main(String[] args) {
    // int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0, 123, -1, -23, 1234, -123, 1 };

    // 8千万 14s
    int[] arr = new int[80000000];
    for(int i = 0; i < 80000000; i++) {
    arr[i] = (int)(Math.random()*800000);
    }
    System.out.println("start....");
    long before = System.currentTimeMillis();
    // System.out.println(Arrays.toString(arr));
    mergeSort(arr, 0, arr.length - 1);
    System.out.printf("use %d ms", System.currentTimeMillis() - before);
    // System.out.println(Arrays.toString(arr));

    // int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
    // merge(arr, 0, 7, 3);
    // System.out.println(Arrays.toString(arr));
  }

  public static void mergeSort(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    // print(arr, left, right);
    int mid = (left + right) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);

    merge(arr, left, right, mid);
    // print(arr, left, right);
  }

  public static void print(int[] arr, int left, int right) {
    for (int i = left; i <= right; i++) {
      System.out.print(i + ", ");
    }
    System.out.println();
  }

  public static void merge(int[] arr, int left, int right, int mid) {
    int leftStart = left;
    int leftEnd = mid;
    int rightStart = mid + 1;
    int rightEnd = right;

    int[] temp = new int[right-left+1];

    for(int i = 0; i <= right-left; i++) {
      if(leftStart == leftEnd+1) {
        temp[i] = arr[rightStart];
        rightStart++;
      } else if(rightStart == rightEnd+1) {
        temp[i] = arr[leftStart];
        leftStart++;
      } else {
        if(arr[leftStart] > arr[rightStart]) {
          temp[i] = arr[rightStart];
          rightStart++;
        } else {
          temp[i] = arr[leftStart];
          leftStart++;
        }
      }
    }

    int k = left;
    for(int i = 0; i <= right-left; i++){
      arr[k++] = temp[i];
    }
  }
}