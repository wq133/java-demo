package com.example.devdemo.customthreadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
          private static final int CORE_POOL_SIZE = 5;
          private static final int MAX_POOL_SIZE = 10;
          private static final int QUEUE_CAPACITY = 100;
          private static final Long KEEP_ALIVE_TIME = 1L;


          public static void main(String[] args) {
                    //使用阿里巴巴推荐的创建线程池的方式
                    //通过ThreadPoolExecutor构造函数自定义参数创建

                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                            MAX_POOL_SIZE,
                            KEEP_ALIVE_TIME,
                            TimeUnit.SECONDS,
                            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                            new ThreadPoolExecutor.CallerRunsPolicy());
                    for (int i = 0; i < 10; i++) {

                              MyRunnableDemoWithGuide runnableDemoWithGuide = new MyRunnableDemoWithGuide("" + i);
                              threadPoolExecutor.execute(runnableDemoWithGuide);
                    }
                    // 终止线程池
                    threadPoolExecutor.shutdown();
                    while (!threadPoolExecutor.isTerminated()){}
                    System.out.println("Finished all threads");
          }


}
