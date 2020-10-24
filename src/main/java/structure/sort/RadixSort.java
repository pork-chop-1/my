package structure.sort;

import java.util.Arrays;

// 基数排序
// 正数。。。
public class RadixSort {
  public static void main(String[] args) {
    int[] arr = {53, 3, 542, 748, 14, 214};
    radixSort(arr);
    System.out.println(Arrays.toString(arr));
  }
  public static void radixSort(int [] arr) {
    // 初始化桶
    int[][] bucket = new int[10][arr.length];
    // 桶子中数字的个数
    int[] bucketElementCount = new int[10];

    int maxNumber = 0;
    for(int i = 0 ; i<arr.length; i++) {
      if(arr[i] > maxNumber) {
        maxNumber = arr[i];
      }
    }
    System.out.println("maxNumber: " + maxNumber);

    int maxDigit = (maxNumber + "").length();
    for(int digit = 0; digit < maxDigit; digit++) {
      for(int i = 0; i<arr.length; i++) {
        // 获得个位数
        int temp = arr[i] /(int)Math.pow(10, digit) % 10;
        // 添加进桶子
        bucket[temp][bucketElementCount[temp]] = arr[i];
        // 添加后个数也添加
        bucketElementCount[temp] ++;
      }
      
      int index = 0;
      for(int i = 0; i < bucketElementCount.length; i++) {
        for(int j = 0; j < bucketElementCount[i]; j++) {
          arr[index++] = bucket[i][j];
        }
        //用完清零
        bucketElementCount[i] = 0;
      }
      System.out.println(Arrays.toString(arr));
    }
  }
}
