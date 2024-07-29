package com.example.devDemo;

import com.example.devDemo.consts.JsonConst;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class JsonCaculateDemo {

    public static void main(String[] args) {
        Boolean aBoolean = caculateJsonSize(JsonConst.jsonString);

        System.out.println("aBoolean = " + aBoolean);
    }

    public static Boolean caculateJsonSize(String jsonString) {

        // 1MB limit
        BigDecimal bodyLimitation = BigDecimal.valueOf(1024 * 1024);
        int byteSize = jsonString.getBytes(StandardCharsets.UTF_8).length;

        BigDecimal jsonByteSize = BigDecimal.valueOf(byteSize);
        // 直接比较大小，判断是否小于1MB
        return jsonByteSize.compareTo(bodyLimitation) <= 0;
    }

}
