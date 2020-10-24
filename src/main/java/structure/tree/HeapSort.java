package structure.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
//第一次将整个数组排成大顶堆的时候就是从后面开始排,而后面这里从开头开始排是因为我们只变动了堆顶元素和末尾元素，而末尾元素
    public static void heapSort(int[] arr) {
        System.out.println("堆排序！");
        int length = arr.length;

        // 第一次的逻辑类似于冒泡排序
        // 从堆的下到上的排序，类似于这个：
        // 4,3,2,1 -> 4,3,1,2 -> 4,1,2,3 -> 1,2,3,4

        for(int i = length/2-1; i > -1; i--){
            adjustHeap(arr, i, length);
        }
        //[9, 6, 8, 5, 4]
        int temp = 0;
        for(int j = length-1 ; j>0; j--) {
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 
     * @param arr    待调整的堆
     * @param i      最后一个
     * @param length arr的长度，可变，越来越短
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; // 暂存
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if(k+1<length && arr[k] < arr[k+1]) {
                k++; // 左边大于右边
            }
            if(arr[k] > temp) {
                arr[i] = arr[k];
                i = k;  //
            } else {
                break; //                
            }
        }
        arr[i] = temp;
    }
}
