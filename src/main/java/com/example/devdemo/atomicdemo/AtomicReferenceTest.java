package com.example.devdemo.atomicdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReferenceTest
 * 引用AtomicReference<Person> ar = new AtomicReference<>(new Person("SnailClimb", 22));
 * get()
 * comparaeAndSet()
 */
public class AtomicReferenceTest {
          public static void main(String[] args) {


                    // 创建 AtomicReference 对象并设置初始值
                    AtomicReference<Person> ar = new AtomicReference<>(new Person("SnailClimb", 22));

                    // 打印初始值
                    System.out.println("Initial Person: " + ar.get().toString());

                    // 更新值
                    Person updatePerson = new Person("Daisy", 20);
                    ar.compareAndSet(ar.get(), updatePerson);

                    // 打印更新后的值
                    System.out.println("Updated Person: " + ar.get().toString());

                    // 尝试再次更新
                    Person anotherUpdatePerson = new Person("John", 30);
                    boolean isUpdated = ar.compareAndSet(updatePerson, anotherUpdatePerson);

                    // 打印是否更新成功及最终值
                    System.out.println("Second Update Success: " + isUpdated);
                    System.out.println("Final Person: " + ar.get().toString());

          }

          public static void main2(String[] args) {
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