package com.example.algorithm;

public class dichotomyDemo {

    /**
     * 二分查找算法实现。
     *
     * @param arr    已排序的整型数组
     * @param target 要查找的目标值
     * @return 目标值在数组中的索引位置，如果不存在则返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 防止(left + right)溢出
            if (arr[mid] == target) {
                return mid; // 找到了目标值，返回其索引
            } else if (arr[mid] < target) {
                left = mid + 1; // 在右半部分继续查找
            } else {
                right = mid - 1; // 在左半部分继续查找
            }
        }

        return -1; // 没有找到目标值
    }

}
