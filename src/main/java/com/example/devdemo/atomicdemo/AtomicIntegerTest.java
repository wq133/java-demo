package com.example.devdemo.atomicdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类的基本概念
 * 原子类，顾名思义，就是用来进行原子操作的类。
 * 在Java中，这些类提供了一种线程安全的方式来操作单个变量，无需使用synchronized关键字。
 * 原子类的操作是基于CAS（Compare-And-Swap，比较并交换）机制实现的，这是一种轻量级的同步策略，比传统的锁机制更高效。
 * <p>
 * 常见的原子类:
 * AtomicInteger：提供了一个可以原子性更新的int值。
 * AtomicLong：和AtomicInteger类似，但它是针对long类型的。
 * AtomicBoolean：提供了一个可以原子性更新的boolean值。
 * AtomicReference：提供了一个可以原子性更新的对象引用。
 */
public class AtomicIntegerTest {

          private static AtomicInteger count = new AtomicInteger(0);

          public static void increment() {
                    count.incrementAndGet(); // 原子性地增加count的值
          }

          public static int getCount() {
                    return count.get();
          }

          /**
           * 基础操作
           *
           * @param args
           */
          public static void main(String[] args) {

                    Map<String, AtomicInteger> map = new HashMap<>();
                    map.put("test",new AtomicInteger(0));
                    // 初始化 AtomicInteger 对象，初始值为 0
                    AtomicInteger atomicInt = map.get("test");

                    int i = atomicInt.addAndGet(1);
                    System.out.println("i = " + i);
                    System.out.println("atomicInt = " + atomicInt);
                    System.out.println("map = " + map.toString());

                    // 使用 getAndSet 方法获取当前值，并设置新值为 3
                    int tempValue = atomicInt.getAndSet(3);
                    System.out.println("tempValue: " + tempValue + "; atomicInt: " + atomicInt);

                    // 使用 getAndIncrement 方法获取当前值，并自增 1
                    tempValue = atomicInt.getAndIncrement();
                    System.out.println("tempValue: " + tempValue + "; atomicInt: " + atomicInt);

                    // 使用 getAndAdd 方法获取当前值，并增加指定值 5
                    tempValue = atomicInt.getAndAdd(5);
                    System.out.println("tempValue: " + tempValue + "; atomicInt: " + atomicInt);

                    // 使用 compareAndSet 方法进行原子性条件更新，期望值为 9，更新值为 10
                    boolean updateSuccess = atomicInt.compareAndSet(9, 10);
                    System.out.println("Update Success: " + updateSuccess + "; atomicInt: " + atomicInt);

                    // 获取当前值
                    int currentValue = atomicInt.get();
                    System.out.println("Current value: " + currentValue);

                    // 使用 lazySet 方法设置新值为 15
                    atomicInt.lazySet(15);
                    System.out.println("After lazySet, atomicInt: " + atomicInt);

          }

          /**
           * 模拟多线程
           *
           * @param args
           */
          public static void main2(String[] args) {
                    AtomicIntegerTest counter = new AtomicIntegerTest();
                    // 会出现并发问题 stream流同理
                    final Integer[] intCounter = {0};
                    // 模拟多线程环境
                    for (int i = 0; i < 1000; i++) {
                              new Thread(() -> {
                                        counter.increment();
                                        intCounter[0]++;
                              }).start();
                    }

                    // 等待所有线程完成
                    try {
                              Thread.sleep(2000); // 等待足够长的时间以确保所有线程都执行完毕
                    } catch (InterruptedException e) {
                              e.printStackTrace();
                    }
                    System.out.println("最终计数: " + counter.getCount()); // 正确输出1000
                    System.out.println("最终计数Integer: " + intCounter[0]); // 正确输出1000
          }

}
