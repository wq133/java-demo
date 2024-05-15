package com.example.devDemo;

import com.alibaba.fastjson2.JSON;
import com.example.devDemo.entity.Student;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListDemo {

          public static void main(String[] args) {
//        List<Student> students = new ArrayList<>();
//
//        Random random = new Random();
//
//        for (int i = 0; i < 10; i++) {
//            Student student = Student.builder()
//                    .id(com.exmample.utils.GenerateUUID.generate())
//                    .name(GenerateRandomChineseName.randomName())
//                    .age(random.nextInt())
//                    .build();
//            students.add(student);
//        }
//        System.out.println("students = " + students);


//        List -> JSON
//        List<Student> students = new ArrayList<>();
//        Object json = JSON.toJSON(students,Student.class);
//        System.out.println("json = " + json);

//        JSON -> List
                    String json = "[{\"age\":-425370594,\"id\":\"ef7f7639-f672-0fff-8642-76187746bbf6\",\"name\":\"段歧金\"},{\"age\":-1299334324,\"id\":\"6fed763b-f672-0fff-9144-0826d60cf825\",\"name\":\"余阑蠕\"},{\"age\":543170365,\"id\":\"efff763b-f672-0fff-ac22-523ec3ac7e9a\",\"name\":\"万市翟\"},{\"age\":-1603299607,\"id\":\"7fed763b-f672-0fff-b8b5-1ffffa5ab33f\",\"name\":\"邵穷浮\"},{\"age\":1393637743,\"id\":\"ff6d763b-f672-0fff-9d40-709d6588b077\",\"name\":\"吕按挥\"},{\"age\":-1279832129,\"id\":\"ff6f763b-f672-0fff-843f-63c5e8193c79\",\"name\":\"冯穆援\"},{\"age\":1899697129,\"id\":\"6fed763b-f672-0fff-af5d-1eea3a394329\",\"name\":\"姜离述\"},{\"age\":-465857009,\"id\":\"ef7f763b-f672-0fff-81b0-a6f4c9b9a295\",\"name\":\"谢梨碧\"},{\"age\":-1187649426,\"id\":\"effd763b-f672-0fff-ab23-fd02d9b53230\",\"name\":\"史苟训\"},{\"age\":1859746532,\"id\":\"ffff763b-f672-0fff-bb65-90ddbc628711\",\"name\":\"石崩葛\"}]\n";
                    List<Student> lists = JSON.parseArray(json, Student.class);
                    System.out.println("lists = " + lists);

                    for (Student list : lists) {
                              System.out.println("list = " + list);
                    }

//      joining 第一个参数:分割的符号/字段
//              第二个参数:集合的首前缀添加字符串 prefix
//              第三个参数:集合的尾后缀 添加 suffix  一般填 "," 拼接其他集合
                    String nameLists = lists
                            .stream()
                            .filter(student -> student.age < 0)
                            .map(Student::getName)
                            .collect(Collectors.joining(",", "段", "++"));
                    System.out.println("nameLists = " + nameLists);

                    // 以id为分组，映射一个Map
                    Map<String, Student> collect = lists.stream().filter(student -> student.age < 0)
                            .collect(Collectors.toMap(Student::getId, Function.identity()));
                    System.out.println("collect = " + collect);

//Student::getId：这是方法引用的使用，用来提取每个Student对象的ID作为Map中的键。它等同于student -> student.getId()，但更简洁易读。
//Function.identity()：正如之前解释的，这个函数表示恒等转换，即输入什么就输出什么，这里用于指定Map的值应该直接使用流中的元素本身（即每个Student对象）。这非常适合你的需求，因为你希望学生对象本身就是Map的值。
//(oldValue, newValue) -> oldValue：这是当遇到键冲突（即两个学生有相同的ID）时的合并函数（merge function）。这里规定如果发现重复的键，保留旧的值（oldValue）。这意味着如果有两个学生对象有相同的ID，最终Map中将只保留第一次遇到的那个学生对象。
                    Map<String, Student> threeMap = lists.stream()
                            .collect(Collectors.toMap(Student::getId,
                                    Function.identity(),
                                    (oldValue, newValue) -> oldValue));
                    System.out.println("threeMap = " + threeMap);

                    List<String> nameListThreeMap = lists.stream().filter(student -> threeMap.keySet().contains(student.getId()))
                            .map(Student::getName)
                            .collect(Collectors.toList());
                    System.out.println("nameListThreeMap = " + nameListThreeMap);

                    // sorted 排序
                    List<Student> afterSortedList = lists.stream().sorted(Comparator.comparing(Student::getAge).reversed())
                            .collect(Collectors.toList());
                    System.out.println("afterSortedList = " + afterSortedList);
                    // limit 限制输出
                    long count = lists.stream().limit(1).count();
                    System.out.println("count = " + count);


//          假设有一个包含整数的列表，如何使用Java Stream 操作找到列表中所有奇数的平方值，并将它们按照降序排列后输出?请完善以下代码。
                    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                    List<Integer> oddSquaresDesc;

                    oddSquaresDesc = numbers.stream()
                            .filter(number -> (number % 2 != 0))
                            .map(number -> number * number)
                            .sorted(Comparator.reverseOrder())
                            .collect(Collectors.toList());
                    oddSquaresDesc = numbers.stream()
                            .filter(number -> (number % 2 != 0))
                            .map(number -> number * number) // 对奇数进行平方
                            .sorted(Comparator.reverseOrder()) // 按降序排列
                            .collect(Collectors.toList());

                    System.out.println("奇数的平方值按降序排列后为：" + oddSquaresDesc);


          }
}
