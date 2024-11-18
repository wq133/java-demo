package com.base.devdemo.utils;

import java.util.Random;

/**
 *
 * demo 用于填充特殊长度的字段
 * 如 varchar 255
 *
 */
public class GenerateStringDemo {

    public static void main(String[] args) {
        // 随机生成255个字母
        String randomString = generateRandomLetterString(255);
        System.out.println(randomString);

        // 生成指定长度的基础字符串
        String randomString2 = generateTestString(255);
        System.out.println(randomString2);


    }

    public static String generateRandomLetterString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        // 字母范围：A-Z 和 a-z
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < length; i++) {
            // 随机选择一个字母
            int index = random.nextInt(letters.length());
            sb.append(letters.charAt(index));
        }

        return sb.toString();
    }


    public static String generateTestString(int length) {
        String baseString = "测试测试测"; // 基础字符串
        StringBuilder sb = new StringBuilder(length);

        // 重复基础字符串直到达到所需长度
        while (sb.length() < length) {
            sb.append(baseString);
        }

        // 截取到指定长度
        return sb.toString().substring(0, length);
    }

}
