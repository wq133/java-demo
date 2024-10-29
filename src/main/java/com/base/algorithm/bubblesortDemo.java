package com.base.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 冒泡排序
 */
public class bubblesortDemo {

    /**
     * 冒泡排序
     * 时间复杂度：最佳：O(n) 最差、平均 O(n^2)
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] selectDemoArray = {1, 88, 77, 91, 42, 99};
        int[] ints = bubbleSort(selectDemoArray);
        System.out.println("bubbleSortString = " + Arrays.stream(ints).boxed().collect(Collectors.toList()));


    }

    /**
     * 冒泡排序
     *
     * @param arr
     * @return arr
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr;
    }

}
