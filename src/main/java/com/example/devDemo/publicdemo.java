package com.example.devDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 执行方式：
 * <p>
 * Stream：串行执行。
 * parallelStream：并行执行。
 * 适用场景：
 * <p>
 * Stream：适用于小数据集或简单的流操作。
 * parallelStream：适用于大数据集或复杂的流操作。
 * 性能：
 * <p>
 * Stream：没有并行化的开销，性能稳定。
 * parallelStream：通过并行化可以提高性能，但也引入了线程管理和同步的开销。
 * 线程使用：
 * <p>
 * Stream：在调用线程中执行。
 * parallelStream：使用 Fork/Join 框架的线程池进行并行执行。
 * 在选择使用 Stream 还是 parallelStream 时，应根据具体需求和数据集大小进行权衡。如果数据集较大且操作复杂，可以考虑使用 parallelStream，但也要注意并行化带来的线程管理和同步问题。如果数据集较小或操作简单，使用 Stream 会更高效。
 */
public class publicdemo {

          public static void main(String[] args) {
                    method(null);
          }

          public static void method(String param) {
                    switch (param) {
                              // 肯定不是进入这里
                              case "sth":
                                        System.out.println("it's sth");
                                        break;
                              // 也不是进入这里
                              case "null":
                                        System.out.println("it's null");
                                        break;
                              // 也不是进入这里
                              default:
                                        System.out.println("default");
                    }
          }
}