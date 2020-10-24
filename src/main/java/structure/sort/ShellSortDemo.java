package structure.sort;

import java.util.Arrays;

public class ShellSortDemo {
  public static void main(String[] args) {
    // int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
    int[] arr = new int[80000000];
    for(int i = 0; i < 80000000; i++) {
    arr[i] = (int)(Math.random()*800000);
    }
    System.out.println("start....");
    long before = System.currentTimeMillis();

    shellSort2(arr);
    System.out.printf("use %d ms", System.currentTimeMillis() - before);
    // System.out.println(Arrays.toString(array));
    // System.out.println(Arrays.toString(arr));
  }

  public static void shellSort(int[] arr) {

    int temp = 0;

    for (int gap = arr.length / 2; gap > 0; gap /= 2) {
      // 一个倒过来的冒泡
      for (int i = 0; i < arr.length - gap; i += 1) {
        for (int j = i; j >= 0; j -= gap) {
          if (arr[j] > arr[j + gap]) {
            temp = arr[j];
            arr[j] = arr[j + gap];
            arr[j + gap] = temp;
          }
        }
      }
      // System.out.println(Arrays.toString(arr));
    }
  }

  public static void shellSort2(int[] arr) {
    for (int gap = arr.length / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < arr.length; i++) {
        int j = i;
        int value = arr[i];
        if(value > arr[i - gap]) {
          continue;
        }
        while(j-gap > -1 && value < arr[j - gap]) {
          arr[j] = arr[j-gap];
          j -= gap;
        }
        arr[j] = value;
      }
    }
  }
}
