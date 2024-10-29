package com.example.devdemo.completableFuture;


import com.example.devdemo.entity.Student;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

//import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 *
 *
 */
public class CompletableFutureDemo {

          private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

          /**
           * 通过 new 关键字创建 CompletableFuture 对象这种使用方式可以看作是将 CompletableFuture 当做 Future 来使用。
           *
           * complete的用法
           * 1.异步/同步 在执行完查询/等业务操作 将结果集返回到CompletableFuture中
           * 后续get到返回到结果集再处理。
           * <p>
           * 疑惑：
           *        在同步方法中调用 CompletableFuture.complete() 是有用的，但它的效果取决于你的应用场景。以下是一些可能的用法：
           *        提前完成：在某些情况下，你可能在同步操作中能够确定某个条件满足，然后提前完成 CompletableFuture。这可以在一些业务逻辑中是有意义的，比如根据某些输入条件决定是否需要继续执行异步操作。
           *        错误处理：在同步方法中，如果发生错误，可以使用 completeExceptionally() 方法标记 CompletableFuture 为异常状态，而不必等待异步操作完成。
           *        测试或调试：在测试或调试时，你可能希望模拟某个异步操作的完成状态，可以在同步方法中调用 complete() 来手动设置状态。
           *        组合逻辑：在某些情况下，你可能会将多个异步操作组合在一起，如果其中一个操作已经完成，你可以在同步方法中调用 complete() 来合并结果。
           *
           * @param args
           */
          public static void main(String[] args) {
                    // 创建一个 CompletableFuture
                    CompletableFuture<String> future = new CompletableFuture<>();

                    // 模拟一些操作（在这里使用主线程）
                    new Thread(() -> {
                              try {
                                        Thread.sleep(2000); // 模拟延迟
                                        future.complete("Hello from the async thread!");
                              } catch (InterruptedException e) {
                                        future.completeExceptionally(e);
                              }
                    }).start();

                    // 在主线程中进行一些工作
                    try {
                              Thread.sleep(3000); // 等待一段时间后完成
                              // 主线程可以调用 complete
                              future.complete("Hello from the main thread!");
                    } catch (InterruptedException e) {
                              future.completeExceptionally(e);
                    }
                    // 主线程等待结果
                    try {
                              String response = future.get();// 阻塞直到 CompletableFuture 完成
                              System.out.println("Received response: " + response);
                    } catch (Exception e) {
                              System.err.println("Failed to get response: " + e.getMessage());
                    }
          }


          /**
           * 场景4 组合 CompletableFuture
           * public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)
           * <p>
           * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn)
           * <p>
           * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn,Executor executor)
           */
          public static void main4(String[] args) {
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
           * 场景3： 异常处理：
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
//                              assertEquals("a Running Exception!", future.get());

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
//                              assertEquals("world!", future.get());

                    } catch (Exception e) {
                              throw new RuntimeException(e);
                    }
          }

          /**
           * 场景2：自定义线程池       执行批量插入/更新/删除操作
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
