package com.example.devDemo.regularMatch;

import java.util.ArrayList;
import java.util.List;

public class Practice_01 {
    public static void main(String[] args) {
        // 首先拿到字符串
        String str = "CaUUUCO3";
        // 获取到 对应元素的字典 存储长度为1 和 2的元素
        String[] dict1 = {"C","O"};
        String[] dict2 = {"Ca"};
        String[] dict3 = {"UUU"};
        // 获取到 元素数
        String[] numList = {"0","1","2","3","4","5","6","7","8","9"};
        List list = new ArrayList();
        // 然后对字符串进行分隔
        // 先参数校验
        if(str != null){
            //在 判断完第一个元素之后再判断第二个元素 是否包含括号？
            // 然后从dict 1中找取元素
            for (int i = 0; i < dict1.length; i++) {
                // 如果dict1中包含元素
                if (dict1[i].equals(str.charAt(i)+"")){
                    // 1. 判断第二位是不是数字
                    Boolean dict2HaveNext = numSearch(str, numList, list, i + 1);
                    // 2. 不是数字？
                    if (!dict2HaveNext){
                        // 如果为假 说明是字母 接着查找
                        // 遍历 dict2
                        for (int i1 = 0; i1 < dict2.length; i1++) {
                            // 如果 dict2中的元素 存在于 str中 则添加到list Ca 退出本次循环
                            if (dict2[i1].equals(str.charAt(i)+""+str.charAt(i+1))){
                                // 判断第三个位是不是一个 数字？
                                Boolean dict3HaveNext = numSearch(str, numList, list, i+2);
                                // 如果 dict2中的元素 存在于 str中 则添加到list Ca 退出本次循环
                                if (!dict3HaveNext){
                                    // 遍历 dict3
                                    for (int i2 = 0; i2 < dict3.length; i2++) {
                                        // 判断dict3 是否包含 首个元素 UUU 并退出本次循环
                                        if (dict3[i2].equals(str.charAt(i)+""+str.charAt(i+1)+""+str.charAt(i+2))){
                                            // str 是有两个字母的元素 添加到list中
                                            list.add(dict3[i2]);
                                            break;
                                        }
                                    }
                                }
                                // str 是有两个字母的元素 添加到list中
                                list.add(dict2[i1]);
                                break;
                            }
                        }
                    }
                    else {
                        // 是数字  要先添加字母 再添加 元素个数
                        // 4. str 是有一个字母的元素 添加到list中
                        list.add(dict1[i]);
                        // 5.
                        break;
                    }
                }
            }
        }else {
            System.out.println("元素传入错误！");
        }


        // 再存到list中
        // 拼接输出
        System.out.println(list);
    }

    /*
        从numlist中取元素
     */
    public static Boolean numSearch(String str,String[] numList,List list,int i){
        for (String num : numList) {
            // 判断 第n位上 是不是 数字 | 是数字 则添加到list 返回 true 否则false
            if (num.toString().equals(str.charAt(i)+"")){
                list.add(num);
                //System.out.println(num);
                return true;
            }
        }
        return false;
    }
}
