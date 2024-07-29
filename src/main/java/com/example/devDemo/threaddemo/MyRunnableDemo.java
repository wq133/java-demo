package com.example.devDemo.threaddemo;

public class MyRunnableDemo implements Runnable{
          @Override
          public void run() {
                    System.out.println("Test My Custome Runnable Service");
          }

          public static void main(String[] args) {
                    MyRunnableDemo myRunnableDemo = new MyRunnableDemo();
                    myRunnableDemo.run();
          }
}
