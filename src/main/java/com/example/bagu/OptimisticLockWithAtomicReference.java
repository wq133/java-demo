package com.example.bagu;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class OptimisticLockWithAtomicReference {

          private static AtomicReference<Integer> value = new AtomicReference<>(0);
          private static AtomicStampedReference<Integer> stampedValue = new AtomicStampedReference<>(0, 0);

          public static void main(String[] args) {
                    // Using AtomicReference
                    System.out.println("Using AtomicReference:");
                    Runnable atomicReferenceTask = () -> {
                              for (int i = 0; i < 5; i++) {
                                        int oldValue = value.get();
                                        System.out.println("Thread: " + Thread.currentThread().getId() +
                                                " Current Value = " + oldValue);
                                        value.compareAndSet(oldValue, oldValue + 1);
                                        System.out.println("Thread: " + Thread.currentThread().getId() +
                                                " Incremented to = " + value.get());
                                        try {
                                                  Thread.sleep(100); // Simulating some processing time
                                        } catch (InterruptedException e) {
                                                  e.printStackTrace();
                                        }
                              }
                    };

                    new Thread(atomicReferenceTask).start();
                    new Thread(atomicReferenceTask).start();

                    // Using AtomicStampedReference
                    System.out.println("\nUsing AtomicStampedReference:");
                    Runnable atomicStampedReferenceTask = () -> {
                              for (int i = 0; i < 5; i++) {
                                        int[] stampHolder = new int[1];
                                        int oldValue = stampedValue.get(stampHolder);
                                        System.out.println("Thread: " + Thread.currentThread().getId() +
                                                " Current Value = " + oldValue);
                                        stampedValue.compareAndSet(oldValue, oldValue + 1, stampHolder[0], stampHolder[0] + 1);
                                        System.out.println("Thread: " + Thread.currentThread().getId() +
                                                " Incremented to = " + stampedValue.get(stampHolder));
                                        try {
                                                  Thread.sleep(100); // Simulating some processing time
                                        } catch (InterruptedException e) {
                                                  e.printStackTrace();
                                        }
                              }
                    };

                    new Thread(atomicStampedReferenceTask).start();
                    new Thread(atomicStampedReferenceTask).start();
          }

}
