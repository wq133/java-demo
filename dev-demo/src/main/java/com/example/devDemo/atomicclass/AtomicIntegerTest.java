package com.example.devDemo.atomicclass;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 原子类的基本概念
 *      原子类，顾名思义，就是用来进行原子操作的类。
 *      在Java中，这些类提供了一种线程安全的方式来操作单个变量，无需使用synchronized关键字。
 *      原子类的操作是基于CAS（Compare-And-Swap，比较并交换）机制实现的，这是一种轻量级的同步策略，比传统的锁机制更高效。
 *
 * 常见的原子类:
 *      AtomicInteger：提供了一个可以原子性更新的int值。
 *      AtomicLong：和AtomicInteger类似，但它是针对long类型的。
 *      AtomicBoolean：提供了一个可以原子性更新的boolean值。
 *      AtomicReference：提供了一个可以原子性更新的对象引用。
 *
 */
public class AtomicIntegerTest {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void increment() {
        count.incrementAndGet(); // 原子性地增加count的值
    }

    public static int getCount() {
        return count.get();
    }

    public static void main(String[] args) {
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
