package com.base.devdemo;

import com.alibaba.fastjson2.JSON;
import com.base.devdemo.entity.Student;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class publicdemo {
    public static void main(String[] args) {

        // 日历表时间获取、转换时间戳
        //getCalendarTime();

        //switchDemo1
//        switchDemo1(null);

        // Java Bean属性的动态特性
//        testJavaBeanDynamicCharacter();

        // CompletableFuture-allOf()
//        allOfDemo1();

        // stream流-anyMatch 用法
//        listStreamAnyMatchDemo();

//        hyfCppDemo();
    }

    /**
     * 寒云峰 cppdemo
     */
    private static void hyfCppDemo() {
//        int a =  48;
//        switch (a){
//            case "0":
//                System.out.println("1" );
//                break;
//            case true:
//                System.out.println("2");
//                break;
//            default:
//                System.out.println("???");
//        }
    }

        /**
         * stream流-anyMatch 用法
         */
    private static void listStreamAnyMatchDemo() {

        // stream流-anyMatch
        List<String> words = Arrays.asList("apple", "banana", "orange", "kiwi");
        boolean anyContainsA = words.stream()
                .anyMatch(word -> word.contains("ange"));

        System.out.println("Any word contains 'a'? " + anyContainsA);

        // list的addAll 方法
        List<String> collect = words.stream().filter(o -> "ange".equals(o)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)) {
            System.out.println("collect is null");
        }
        ;
        List<String> arrayList = new ArrayList<>();
        collect.addAll(arrayList);
        System.out.println("collect = " + collect);
    }

    /**
     * CompletableFuture-allOf()
     */
    private static void allOfDemo1() {
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
    }

    /**
     * Java Bean属性的动态特性
     */
    public static void testJavaBeanDynamicCharacter() {
        Student student = new Student();
        System.out.println("student = " + student);//        student = Student(id=null, name=null, age=null, role=null)
        String s = JSON.toJSONString(student);
        System.out.println("s = " + s);//        s = {"localDress":"localDressTest"}
    }


    /**
     * Calendar （日历表） 用法
     */
    public static void getCalendarTime() {

        // Calendar （日历表） 用法
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.HOUR, -(Integer.parseInt("24")));
        Date time = instance.getTime();
        System.out.println("time = " + time);
        // getTime获取时间戳
        System.out.println("time = " + time.getTime());

        Map<String, Map<String, List<Student>>> testMap = new HashMap<>();
        Map<String, List<Student>> testingMap = new HashMap<>();
        List<Student> students = new ArrayList<>();
        testingMap.put("1", students);
        testMap.put("1", testingMap);
        System.out.println("testMap = " + testMap);
    }

    /**
     * switchDemo
     *
     * @param param
     */
    public static void switchDemo1(String param) {
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