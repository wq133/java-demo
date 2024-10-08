package com.example.devdemo.threadPoolExecutorDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {

          private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

          /**
           * ThreadPoolExecutor   的invokeAny方法 test
           * invokeAny(Collection<? extends Callable<T>> tasks)
           * 执行给定的任务集合，返回一个已经成功完成的任务的结果（如果有多个任务成功，则返回其中一个的结果）。
           * 一旦某个任务成功完成，取消所有其他任务。
           */
          public static void main6(String[] args) throws Exception {
                    ExecutorService executor = Executors.newFixedThreadPool(2);

                    List<Callable<String>> tasks = Arrays.asList(
                            () -> {
                                      System.out.println("Task 1: Executing...");
                                      Thread.sleep(2000);
                                      return "Result of Task 1";
                            },
                            () -> {
                                      System.out.println("Task 2: Executing...");
                                      Thread.sleep(1000);
                                      return "Result of Task 2";
                            }
                    );

                    String result = executor.invokeAny(tasks);
                    System.out.println("First completed task result: " + result);

                    executor.shutdown();
          }

          /**
           * ThreadPoolExecutor   的invokeAll方法
           * invokeAll(Collection<? extends Callable<T>> tasks):
           * 执行给定的任务集合，并返回一个包含 Future 对象的列表，用于表示每个任务的状态和结果。
           * 所有任务都完成后返回结果列表。
           * <p>
           * 尽管任务是并发执行的，但是 Future.get() 方法的调用顺序与 tasks 列表中 Callable 任务的顺序相对应。
           * 这意味着，当你依次调用每个 Future 的 get() 方法时，它们会按照任务被提交的顺序返回结果，而不是按照任务完成的顺序。
           *
           * @param args
           * @throws Exception
           */
          public static void main5(String[] args) throws Exception {

                    List<Callable<String>> tasks = Arrays.asList(
                            () -> {
                                      System.out.println("Task 1: Executing...");
                                      Thread.sleep(2000);
                                      return "Result of Task 1";
                            },
                            () -> {
                                      System.out.println("Task 2: Executing...");
                                      Thread.sleep(1000);
                                      return "Result of Task 2";
                            }
                    );

                    List<Future<String>> futures = executor.invokeAll(tasks);

                    for (Future<String> future : futures) {
                              System.out.println(future.get());
                    }

                    executor.shutdown();
          }

          /***
           * ThreadPoolExecutor的execute方法
           *
           * execute(Runnable command):
           * 用于执行一个不返回结果的异步任务。
           * 无法直接获取任务的执行结果或捕获异常。
           */
          public static void main4(String[] args) {
                    executor.execute(() -> {
                              System.out.println("_____________________________");
                              try {
                                        Thread.sleep(3000);
                                        System.out.println("result1 :" + "Ok");
                              } catch (Exception e) {
                                        e.printStackTrace();
                              }
                    });
                    executor.execute(() -> {
                              System.out.println("_____________________________");
                              try {
                                        Thread.sleep(2000);
                                        System.out.println("result2 :" + "Ok");
                              } catch (Exception e) {
                                        e.printStackTrace();
                              }
                    });

                    try {
                              System.out.println("Test is Ok?");
                    } catch (Exception e) {
                              e.printStackTrace();
                              throw new RuntimeException(e);
                    } finally {
                              executor.shutdown();
                    }
          }

          /***
           * ThreadPoolExecutor的submit方法
           *
           * submit 方法的主要作用
           * 提交任务: 将一个 Runnable 或 Callable 任务提交给线程池执行。
           * 获取结果: 返回一个 Future 对象，通过该对象可以获取任务的结果或检查任务的状态。
           * 处理异常: 可以捕获并处理任务执行过程中发生的异常。
           */
          public static void main3(String[] args) {

                    Future<String> submit1 = executor.submit(() -> {
                              System.out.println("ssssssssssssssssssssssss");
                              try {
                                        Thread.sleep(3000);
                                        System.out.println("result :" + "Okkkkkkkkkkkkkkk");
                                        return "success";
                              } catch (Exception e) {
                                        e.printStackTrace();
                                        return "ok";
                              }
                    });

                    try {
                              System.out.println("submit1.get() = " + submit1.get());
                    } catch (Exception e) {
                              e.printStackTrace();
                              throw new RuntimeException(e);
                    } finally {
                              executor.shutdown();
                    }
          }

}
