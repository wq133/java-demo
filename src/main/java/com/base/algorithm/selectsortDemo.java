package com.base.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n2) 的时间复杂度。
 * 所以用到它的时候，数据规模越小越好。
 * 唯一的好处可能就是不占用额外的内存空间了吧。
 *
 * 它的工作原理：
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 重复第 2 步，直到所有元素均排序完毕。
 */
public class selectsortDemo {

    public static void main(String[] args) {
        int [] selectDemoArray = {1,88,77,91,42,99};
        int[] ints = selectionSort(selectDemoArray);
        System.out.println("selectionSort String = " + Arrays.stream(ints).boxed().collect(Collectors.toList()));
    }

    /**
     * 选择排序
     * 时间复杂度 n^2
     * @param arr
     * @return arr
     */
    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {// 理解这里时间复杂度为n
            int minIndex = i;
            // 找到最小的元素的索引
            for (int j = i + 1; j < arr.length; j++) {// xxxxxx 这里时间复杂度也近似为n （ 比如数组长度为 5 ，第一次i=1 遍历4次 i=2 遍历3次 i=3 遍历2次 i=4 遍历1次 ）
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 当前元素 不是 剩余（i之后的数组元素）数组元素中最小的元素
            if (minIndex != i) {
                // 就交换 当前元素和 最小元素的位置 完成排序
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
        return arr;
    }

}
