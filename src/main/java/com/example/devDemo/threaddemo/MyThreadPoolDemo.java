package com.example.devDemo.threaddemo;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class MyThreadPoolDemo {


          public static void main(String[] args) {
                    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
                    threadPoolTaskExecutor.setCorePoolSize(8);
                    threadPoolTaskExecutor.setMaxPoolSize(10);
                    MyThread myThread = new MyThread();
                    threadPoolTaskExecutor.initialize();
                    for (int i = 0; i < 10; i++) {
                              threadPoolTaskExecutor.execute(myThread);
                    }
                    threadPoolTaskExecutor.shutdown();
                    //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 10, 1, TimeUnit.MINUTES);

          }
}
