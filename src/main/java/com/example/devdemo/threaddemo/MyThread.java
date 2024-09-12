package com.example.devdemo.threaddemo;

public class MyThread extends Thread{

          @Override
          public void run() {
                    System.out.println("Thread created by extending Thread class.");
          }

          public static void main(String[] args) {
                    MyThread myThread = new MyThread();
                    myThread.run();
          }
}
