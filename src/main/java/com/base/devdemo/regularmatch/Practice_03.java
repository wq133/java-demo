package com.base.devdemo.regularmatch;

import java.util.ArrayList;
import java.util.List;

public class Practice_03 {
        public static void main(String[] args) {
            //String str = "CaCO3";
            String str = "Fe2(SO4)3";
            // 记录 元素 出现的个数
            List<Integer> num = new ArrayList<>();
            // 记录 出现的元素
            List<String> list = new ArrayList<>();
            // 记录 () 在list中出现的位置
            List<Integer> ll = new ArrayList<>();
            int len = str.length();
            // 以字符串的长度为范围，遍历
            for (int i = 0; i < len; i++) {
                // 首先判断是否是大写字母
                if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                    int j = i;
                    i++;
                    while (i < len && (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')) {
                        i++;
                    }
                    // 从第j个大写字母到下一个大写字母出现之前，存入list中
                    list.add(str.substring(j, i));
                    // 判断大写字母后面有无数字，并将字符串的数字转换为个数
                    if (i < len && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                        int sum = 0;
                        while (i < len && (str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                            // - '0' 为了让字符 自动转型成 integer 避免直接转换成对应的 ascii码表
                            sum = sum * 10 + (str.charAt(i) - '0');
                            ++i;
                        }
                        num.add(sum);
                    } else {
                        //  字母后面没有数字，则字母出现次数是 1
                        num.add(1);
                    }
                    // 避免出现 outofRange异常
                    i--;
                } else if ('(' == (str.charAt(i))) {
                    // 如果出现 ( 则记录是在哪一个元素前出现的
                    ll.add(list.size());
                } else {
                    //  ) 的情况，计算 ) 后面的数字。
                    i++;
                    int sum = 0;
                    // 当i不超过长度 且数字在0 - 9的范围内 记录到num的list中
                    while (i < len && (str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                        sum = sum * 10 + (str.charAt(i) - '0');
                        i++;
                    }
                    i--;
                    // 判断是否有数字出现
                    sum = sum == 0 ? 1 : sum;
                    // 以元素长度为范围，遍历更新
                    for (int j = ll.get(ll.size() - 1); j < list.size(); j++) {
                        // 这里更新 num 为运算完的元素个数
                        int count = num.get(j) * sum;
                        num.remove(j);
                        num.add(j, count);
                    }
                    // 将最新的 ( 去除掉
                    ll.remove(ll.size() - 1);
                }
            }
            // 遍历list 输出结果
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + num.get(i));
            }
        }
}
