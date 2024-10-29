package com.base.devdemo.threaddemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallableDemo implements Callable {
          @Override
          public Object call() throws Exception {
                    System.out.println("My Own CallableDemo is Run");
                    return null;
          }

          public static void main(String[] args) {

                    MyCallableDemo myCallableDemo = new MyCallableDemo();

                    FutureTask futureTask = new FutureTask<>(myCallableDemo);
                    futureTask.run();


          }
}
