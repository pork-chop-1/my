package structure.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class SimpleSort {
  public static void main(String[] args) {
    int[] array = { -2, 17, 10, 5, -7, 0, 3, 6 };
    // int[] array = new int[80000];
    insertSort(array);
    System.out.println(Arrays.toString(array));
    // Random r = new Random();
    // for(int i = 0; i < 80000; i++) {
    // array[i] = (int)(Math.random()*800000);
    // }
    // System.out.println(r.nextInt(800000));
    // System.out.println("start....");
    // long before = System.currentTimeMillis();
    // bubbleSort(array, (lhs, rhs) -> {
    // return lhs-rhs;
    // });
    // System.out.printf("use %d ms", System.currentTimeMillis() - before);
    // System.out.println(Arrays.toString(array));
    // Arrays.sort(array);
  }

  public static void bubbleSort(int[] array, Comparator<Integer> c) {
    boolean flag = false;
    for (int i = array.length - 1; i > -1; i--) {
      for (int j = 0; j < i; j++) {
        if (c.compare(array[j], array[j + 1]) < 0) {
          flag = true;
          int t = array[j];
          array[j] = array[j + 1];
          array[j + 1] = t;
        }
      }
      if (flag == false) {
        break;
      } else {
        flag = false;
      }
    }
  }

  // 找到最小的数就往前面堆
  public static void selectSort(int[] arr) {

    for (int i = 0; i < arr.length - 1; i++) {
      int minLoc = i;
      for (int j = i; j < arr.length; j++) {
        if (arr[minLoc] > arr[j]) {
          minLoc = j;
        }
      }
      int temp = arr[minLoc];
      arr[minLoc] = arr[i];
      arr[i] = temp;
    }
  }
  // 1,5,3,2,6
  // i=2,j=1

  public static void insertSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) { // i代表被插入的index
      // int j = 0;
      // for (; j < i; j++) {
      //   if (arr[i] < arr[j]) {
      //     break;
      //   }
      // }
      // if (i == j) {
      //   continue;
      // }
      // int temp = arr[i];
      // for (int k = i; k > j; k--) { // 往后退
      //   arr[k] = arr[k - 1];
      // }
      // arr[j] = temp;

      int j = i;
      int value = arr[j];
      if (value > arr[j - 1]) {
        continue;
      }
      for (; j - 1 > -1 && arr[j - 1] > value; j--) {
        arr[j] = arr[j - 1];
      }
      arr[j] = value;
    }
  }
}
