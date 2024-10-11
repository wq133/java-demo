package com.example.devdemo;

import com.alibaba.fastjson2.JSON;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapDemo {
    public static void main(String[] args) {

    /* （Stream流的用法）

        map 操作后返回新的流
        Collectors collect 把abc放入容器中:
        List<String> collect = strings.stream().filter(string -> "abc".equals(string)).collect(Collectors.toList());
        flatMap:    flatMap与map最直观的区别就是 flatMap返回的是流, 而map是流内元素
        .peek(Consumer < T >) //Consumer<T> 就是对流元素的一种消费/操作。（一般用于不想改变流中元素本身的类型或者只想操作元素的内部状态时）
        .anyMactch(list2::contains).reduce 见下边

        toMap()
            两个参数:如果映射的键包含重复项，则在执行收集操作时会抛出IllegalStateException。
            如果映射的键可能有重复项，请改用 toMap(Function, Function, BinaryOperator)。
            三个参数:只取后一个key及value：

    */


//        List<String> list = Arrays.asList("a", "b", "c", "d");
//        Map<String, String> map = list.stream()
//                .collect(Collectors.toMap(Function.identity(), s -> s.toUpperCase()));
//        System.out.println("map = " + map);


        String carCode = "京A88888";
        String nowProvince = "北京";

        String str = "{\"京\":\"北京\",\"豫\":\"河南\",\"湘\":\"湖南\"}";
        Map<String,String> maps = (Map)JSON.parse(str);
        System.out.println("maps = " + maps);

        Set<String> strings = maps.keySet();
        String nowProviceCode = "";
        for (String provinceCarCode : strings) {
            if (carCode.startsWith(provinceCarCode)){
                nowProviceCode = maps.get(provinceCarCode);
                continue;
            }
        }

        if (nowProviceCode == ""){
            throw new RuntimeException("未匹配到省份！");
        }
        if (!nowProvince.equals(nowProviceCode)){
            throw new RuntimeException("当前省市与车牌不匹配！");
        }

        return ;

    }
}
