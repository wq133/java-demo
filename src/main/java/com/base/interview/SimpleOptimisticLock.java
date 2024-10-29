package com.base.interview;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleOptimisticLock {
          private AtomicInteger value = new AtomicInteger(0);

          public int getValue() {
                    return value.get();
          }

          public boolean increment() {
                    int oldValue, newValue;
                    do {
                              oldValue = value.get();
                              newValue = oldValue + 1;
                    } while (!value.compareAndSet(oldValue, newValue));

                    return true;
          }

          public static void main(String[] args) {
                    SimpleOptimisticLock lock = new SimpleOptimisticLock();

                    // Thread 1
                    new Thread(() -> {
                              for (int i = 0; i < 5; i++) {
                                        int oldValue = lock.getValue();
                                        System.out.println("Thread 1: Current Value = " + oldValue);
                                        lock.increment();
                                        System.out.println("Thread 1: Incremented to = " + lock.getValue());
                                        try {
                                                  Thread.sleep(100); // Simulating some processing time
                                        } catch (InterruptedException e) {
                                                  e.printStackTrace();
                                        }
                              }
                    }).start();

                    // Thread 2
                    new Thread(() -> {
                              for (int i = 0; i < 5; i++) {
                                        int oldValue = lock.getValue();
                                        System.out.println("Thread 2: Current Value = " + oldValue);
                                        lock.increment();
                                        System.out.println("Thread 2: Incremented to = " + lock.getValue());
                                        try {
                                                  Thread.sleep(100); // Simulating some processing time
                                        } catch (InterruptedException e) {
                                                  e.printStackTrace();
                                        }
                              }
                    }).start();
          }
}
