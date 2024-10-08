package com.example.devdemo.completableFuture;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class CompletableFutureDemo {

          private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

          /**
           * 场景2：
           * 自定义线程池执行批量插入/更新/删除操作
           * <p>
           * CompletableFuture方法应用：
           * join()方法:    等待任务完成: 使用 join() 方法等待任务完成。
           * runAsync: 用于异步执行一个没有返回值的任务。返回一个 CompletableFuture<Void>，表示任务的完成状态。
           * supplyAsync: 用于异步执行一个有返回值的任务。返回一个 CompletableFuture<T>，表示任务的结果。
           * 两者都可以使用自定义的 Executor 来指定线程池。如果不指定，默认会使用 ForkJoinPool 公共线程池。
           * <p>
           * executor.shutdown();线程池使用完需要关闭
           */
          public static void main2(String[] args) {
                    CompletableFuture<Void> fuckCompletableFuture = CompletableFuture.runAsync(() -> {
                              //...
                              for (int i = 0; i < 5; i++) {
                                        System.out.println("fuck fuck fuck done...");
                              }
                    }, executor);
                    fuckCompletableFuture.join();
                    executor.shutdown();
          }

          /**
           * 场景1：
           * 多个service 需要completableFuture异步执行，执行时间取决于最短的那个执行时间
           * <p>
           * CompletableFuture方法应用：
           * supplyAsync(() -> } {})方法
           * allof方法: 检查 isDone() 的返回值: isDone() 只是检查任务是否已经完成，并不会阻塞等待任务完成。
           */
          public static void main1(String[] args) throws ExecutionException, InterruptedException {
                    AtomicLong total = new AtomicLong();

                    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
                              try {
                                        int nextInt = 10;
                                        Thread.sleep(3000);
                                        total.addAndGet((long) nextInt);
                              } catch (Exception e) {
                                        e.printStackTrace();
                              } finally {
                                        System.out.println("future1 done...");
                              }
                              return "" + total;
                    });
                    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
                              try {
                                        int nextInt2 = 20;
                                        Thread.sleep(5000);
                                        total.addAndGet((long) nextInt2);
                              } catch (Exception e) {
                                        e.printStackTrace();
                              } finally {
                                        System.out.println("future2 done...");
                              }
                              return "" + total;
                    });

                    CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);
                    while (!allOf.isDone()) {
                              Thread.sleep(1000);
                              System.out.println("wait, in loading-------------------");
                    }
                    System.out.println("total = " + total);
          }

          /**
           * CompletableFuture方法应用：
           * supplyAsync(() -> } {})方法
           * thenRun 方法:
           * thenRun(Runnable action): 在当前任务完成后，同步地运行给定的 Runnable 任务。
           * thenRunAsync(Runnable action): 在当前任务完成后，异步地运行给定的 Runnable 任务。默认情况下，它使用公共的 ForkJoin 池来运行任务。
           * thenRunAsync(Runnable action, Executor executor): 在当前任务完成后，使用指定的 Executor 异步地运行给定的 Runnable 任务。
           * <p>
           * 自定义线程池执行:
           * executor
           */
          public static void main(String[] args) {
                    AtomicLong total = new AtomicLong();

                    CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
                              try {
                                        int nextInt = 10;
                                        total.addAndGet((long) nextInt);
                                        Thread.sleep(1000);
                                        total.addAndGet(1L);
                                        System.out.println("future1 ------------- isDone ");
                              } catch (Exception e) {
                                        e.printStackTrace();
                              }
                    }, executor);

                    CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
                              try {
                                        int nextInt = 20;
                                        total.addAndGet((long) nextInt);
                                        Thread.sleep(3000);
                                        total.addAndGet(3L);
                                        System.out.println("future2 ------------- isDone ");
                              } catch (Exception e) {
                                        e.printStackTrace();
                              }
                    }, executor);

                    //
                    CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);

                    allOf.thenRun(() -> {
                              System.out.println("total = " + total);
                              allOf.join();
                              executor.shutdown();
                    });
          }

}
