package com.example.devDemo.atomicdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
          public static void main(String[] args) {
                    final Long[] totalCount = {0L};
                    AtomicReference<Long> value = new AtomicReference<>(0L);
                    AtomicInteger atomIntegerCount = new AtomicInteger();
                    List<Long> list = new ArrayList<>();
                    for (int i = 0; i < 1000; i++) {
                              list.add(1L);
                    }
                    // stream不会出现多线程的并发情况
                    // parallelStream会出现多线程的并发情况
                    list.parallelStream().forEach(num -> totalCount[0] += num);
                    list.parallelStream().forEach(num -> atomIntegerCount.incrementAndGet());
                    System.out.println("totalCount[0] = " + totalCount[0]);
                    System.out.println("atomIntegerCount = " + atomIntegerCount.get());

                    // 加入原子类 不会出现因为并发情况而出现的原子性问题
                    list.forEach(num -> {
                              totalCount[0]++;
                              value.getAndSet(value.get() + 1);
                    });

                    System.out.println("totalCount = " + totalCount[0]);

          }

}