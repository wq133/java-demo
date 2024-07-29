package com.example.devDemo.threadLocal;

/**
 * threadLocal
 * new、get、set方法
 * threadLocal.remove(); 删除方法
 * threadLocal.clear();方法会清理当前线程的 ThreadLocal 变量。具体来说，它只会移除当前线程中与这个 ThreadLocal 实例相关联的值。每个线程都有自己的 ThreadLocal 值，因此调用 clear() 只会影响调用该方法的线程，而不会影响其他线程的 ThreadLocal 值。
 *
 * 从源码可以看出，
 * 每个线程都有自己的ThreadLocalMap，map里面的key为ThreadLocal，value为对应的值
 * 所以ThreadLocal在每个线程中是互不干扰的，这样同一个线程取到ThreadLocal里面的值是一样的，不同线程之间互不干扰，这样就达到了同一个线程中值的传递
 *
 * https://juejin.cn/post/6844903876529750030?searchId=202407111625372732B55F140282BCCDA6
 *
 * 高级用法
 * https://juejin.cn/post/7182719513274613797?searchId=202407111625372732B55F140282BCCDA6#heading-11
 */
public class ThreadLocalTest {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String get() {
        return threadLocal.get();
    }

    public static void set(String value) {
        threadLocal.set(value);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            final int j = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    ThreadLocalTest.set(j + "");
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalTest.get());
                }
            });

            t.start();
        }
    }
}
