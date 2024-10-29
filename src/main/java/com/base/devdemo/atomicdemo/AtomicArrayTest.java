package com.base.devdemo.atomicdemo;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 数组类型原子类常用方法
 * public final int get(int i) //获取 index=i 位置元素的值
 * public final int getAndSet(int i, int newValue)//返回 index=i 位置的当前的值，并将其设置为新值：newValue
 * public final int getAndIncrement(int i)//获取 index=i 位置元素的值，并让该位置的元素自增
 * public final int getAndDecrement(int i) //获取 index=i 位置元素的值，并让该位置的元素自减
 * public final int getAndAdd(int i, int delta) //获取 index=i 位置元素的值，并加上预期的值
 * boolean compareAndSet(int i, int expect, int update) //如果输入的数值等于预期值，则以原子方式将 index=i 位置的元素值设置为输入值（update）
 * public final void lazySet(int i, int newValue)//最终 将index=i 位置的元素设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
 */
public class AtomicArrayTest {
          public static void main(String[] args) {
                    int[] nums = {1, 2, 3, 4, 5, 6};
                    // 创建 AtomicIntegerArray
                    AtomicIntegerArray atomicArray = new AtomicIntegerArray(nums);

                    // 打印 AtomicIntegerArray 中的初始值
                    System.out.println("Initial values in AtomicIntegerArray:");
                    for (int j = 0; j < nums.length; j++) {
                              System.out.print("Index " + j + ": " + atomicArray.get(j) + " ");
                    }
                    System.out.println("_____________________");


                    // 使用 getAndSet 方法将索引 0 处的值设置为 2，并返回旧值
                    int tempValue = atomicArray.getAndSet(0, 2);
                    System.out.println("\nAfter getAndSet(0, 2):");
                    System.out.println("Returned value: " + tempValue);
                    for (int j = 0; j < atomicArray.length(); j++) {
                              System.out.print("Index " + j + ": " + atomicArray.get(j) + " ");
                    }
                    System.out.println("_____________________");


                    // 使用 getAndIncrement 方法将索引 0 处的值加 1，并返回旧值
                    tempValue = atomicArray.getAndIncrement(0);
                    System.out.println("\nAfter getAndIncrement(0):");
                    System.out.println("Returned value: " + tempValue);
                    for (int j = 0; j < atomicArray.length(); j++) {
                              System.out.print("Index " + j + ": " + atomicArray.get(j) + " ");
                    }
                    System.out.println("_____________________");


                    // 使用 getAndAdd 方法将索引 0 处的值增加 5，并返回旧值
                    tempValue = atomicArray.getAndAdd(0, 5);
                    System.out.println("\nAfter getAndAdd(0, 5):");
                    System.out.println("Returned value: " + tempValue);
                    for (int j = 0; j < atomicArray.length(); j++) {
                              System.out.print("Index " + j + ": " + atomicArray.get(j) + " ");
                    }

          }
}
