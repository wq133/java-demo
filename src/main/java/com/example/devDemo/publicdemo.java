package com.example.devDemo;

import com.example.devDemo.entity.Student;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


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
            });

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
            });

            CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);

            allOf.join();
            System.out.println("total = " + total);


//        List<String> words = Arrays.asList("apple", "banana", "orange", "kiwi");
//
//        boolean anyContainsA = words.stream()
//                .anyMatch(word -> word.contains("ange"));
//
//        System.out.println("Any word contains 'a'? " + anyContainsA);
//
//        List<String> collect = words.stream().filter(o -> "ange".equals(o)).collect(Collectors.toList());
//        if (CollectionUtils.isEmpty(collect)){
//            System.out.println("collect is null" );
//        };
//        List<String> arrayList = new ArrayList<>();
//        collect.addAll(arrayList);
//        System.out.println("collect = " + collect);
    }

    public static void main3(String[] args) {
//                    method(null);

        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.HOUR, -(Integer.parseInt("24")));
        Date time = instance.getTime();
        // 时间戳
        System.out.println("time = " + time.getTime());

        Map<String, Map<String, List<Student>>> testMap = new HashMap<>();
        Map<String, List<Student>> testingMap  = new HashMap<>();
        List<Student> students = new ArrayList<>();
        testingMap.put("1",students);
        testMap.put("1", testingMap);
        System.out.println("testMap = " + testMap);
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