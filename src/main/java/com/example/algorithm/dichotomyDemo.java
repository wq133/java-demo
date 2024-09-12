package com.example.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * 二分查找
 * 时间复杂度为 O(log(n))
 *
 * 理解：查找n次 每次都是 n/2的折半查询，查询k次
 *
 * ∵存在 n/2^k = 1 就是查询到最后一个元素才是我们需要的元素。
 *  ∴ k = log2 (n)
 * 又∵ 忽略常数
 *  ∴ 二分查找的 时间复杂度为 O(log(n))
 *
 *  参考链接：https://blog.csdn.net/weixin_42001592/article/details/128963283
 */
public class dichotomyDemo {

    public static void main(String[] args) {
        int[] intArry = {1, 2, 3, 7, 5, 9, 6, 5, 0, 22};
        // 排序
        intArry = Arrays.stream(intArry)
                .boxed()  // 将 int[] 转换为 Integer[]，因为 Comparator 用于引用类型
                .sorted(Comparator.naturalOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println("intArry.toString() = " + Arrays.stream(intArry).boxed().collect(Collectors.toList()));
        // 索引 0-10
        int i = binarySearch(intArry, 22);
        System.out.println("i = " + i);
    }

    /**
     * 二分查找算法实现
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
                left = mid + 1; // 左方指针 在右半部分继续查找
            } else {
                right = mid - 1; // 右方指针 在左半部分继续查找
            }
        }
        return -1; // 没有找到目标值
    }

}
