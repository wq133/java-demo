package com.base.interview;

import java.util.concurrent.atomic.AtomicStampedReference;

public class OptimisticLockWithStampedReference {
          private static final AtomicStampedReference<Integer> value = new AtomicStampedReference<>(0, 0);

          public static int getValue() {
                    return value.getReference();
          }

          public static boolean increment() {
                    int[] stampHolder = new int[1];
                    int oldValue, newValue;
                    do {
                              oldValue = value.get(stampHolder);
                              newValue = oldValue + 1;
                    } while (!value.compareAndSet(oldValue, newValue, stampHolder[0], stampHolder[0] + 1));

                    return true;
          }

          public static void main(String[] args) {
                    // Thread 1
                    new Thread(() -> {
                              for (int i = 0; i < 5; i++) {
                                        int oldValue = getValue();
                                        System.out.println("Thread 1:"+i+" Current Value = " + oldValue);
                                        increment();
                                        System.out.println("Thread 1:"+i+" Incremented to = " + getValue());
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
                                        int oldValue = getValue();
                                        System.out.println("Thread 2:"+i+" Current Value = " + oldValue);
                                        increment();
                                        System.out.println("Thread 2:"+i+" Incremented to = " + getValue());
                                        try {
                                                  Thread.sleep(100); // Simulating some processing time
                                        } catch (InterruptedException e) {
                                                  e.printStackTrace();
                                        }
                              }
                    }).start();
          }
}
