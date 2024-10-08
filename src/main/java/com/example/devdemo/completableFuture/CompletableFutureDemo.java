package com.example.devdemo.completableFuture;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CompletableFutureDemo {

          private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());


          /**
           * 场景4 组合 CompletableFuture
           * public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)
           * <p>
           * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn)
           * <p>
           * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn,Executor executor)
           */
          public static void main(String[] args) {
                    CompletableFuture<String> completableFuture
                            = CompletableFuture.supplyAsync(() -> "hello!")
                            .thenCombine(CompletableFuture.supplyAsync(
                                    () -> "world!"), (s1, s2) -> s1 + s2)
                            .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "nice!"));
                    try {
                              String s = completableFuture.get();
                              System.out.println(s);
                    } catch (Exception e) {
                              throw new RuntimeException(e);
                    }

          }


          /**
           * 场景3：
           * 异常处理：
           * public <U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)}
           * <p>
           * public <U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn)
           * (BiFunction<? super T, Throwable, ? extends U> fn, Executor executor)
           */
          public static void main3(String[] args) {
                    try {
                              CompletableFuture<String> future
                                      = CompletableFuture.supplyAsync(() -> {
                                        if (true) {
                                                  throw new RuntimeException("Computation error!");
                                        }
                                        return "hello!";
                              }).handle((result, exception) -> {
                                        // res 代表返回的结果
                                        // ex 的类型为 Throwable ，代表抛出的异常
                                        return result != null ? result : "a Running Exception!";
                              });
                              // 断言捕获异常 前后对应
                              assertEquals("a Running Exception!", future.get());

                              CompletableFuture<String> future2
                                      = CompletableFuture.supplyAsync(() -> {
                                        if (true) {
                                                  throw new RuntimeException("Computation error!");
                                        }
                                        return "hello!";
                              }).exceptionally(ex -> {
                                        System.out.println(ex.toString());// CompletionException
                                        return "world!";
                              });

                              // 断言捕获异常抛出的world 前后对应
                              assertEquals("world!", future.get());

                    } catch (Exception e) {
                              throw new RuntimeException(e);
                    }
          }

          /**
           * 场景2：
           * 自定义线程池执行批量插入/更新/删除操作
           * <p>
           * CompletableFuture方法应用：
           * join()方法:           等待任务完成: 使用 join() 方法等待任务完成。
           * static CompletableFuture<Void> runAsync(Runnable runnable)
           * (Runnable runnable, Executor executor);
           * 接受的参数是 Runnable，这是一个函数式接口，不允许返回值。
           * 当你需要异步操作且不关心返回结果的时候可以使用 runAsync() 方法。
           * 返回一个 CompletableFuture<Void>，表示任务的完成状态。
           * 两者都可以使用自定义的 Executor 来指定线程池。如果不指定，默认会使用 ForkJoinPool 公共线程池。
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
           * 并行运行多个 CompletableFuture
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
           * 创建 CompletableFuture常见方法如下：
           * 1、通过 new 关键字。基于 CompletableFuture 自带的静态工厂方法：runAsync()、supplyAsync() 。
           * 2、基于 CompletableFuture 自带的静态工厂方法：runAsync()、supplyAsync()
           * supplyAsync(() -> {})：
           * <p>
           * 处理异步结算的结果
           * thenRun (Runnable action): 在当前任务完成后，同步地运行给定的 Runnable 任务。
           * thenRunAsync(Runnable action): 在当前任务完成后，异步地运行给定的 Runnable 任务。默认情况下，它使用公共的 ForkJoin 池来运行任务。
           * (Runnable action, Executor executor): 在当前任务完成后，使用指定的 Executor 异步地运行给定的 Runnable 任务。
           * <p>
           * 自定义线程池执行:
           * executor
           */
          public static void main0(String[] args) {
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
