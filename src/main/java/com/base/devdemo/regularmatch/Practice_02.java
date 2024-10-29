package com.base.devdemo.regularmatch;

import java.util.regex.Pattern;

/***
 * 比如：linux命令行下输入，ls *.txt 就是列出当前目录下所有的结尾是.txt的文件。
 *          下面一段函数实现了带有$和*两种通配符的字符串的匹配功能。
 *          其中：$表示长度大于0的数字串     *表示任意长度的字符串。
 *          要求：按照自己对于算法的理解填写该函数的5个空白。
 *
 * 请注意： 必须是完全匹配才能返回true，
 *      比如 1.txt, a.txt可以匹配*.txt,
 *      2.tx 或 者 2.txta 不能匹配 *.txt。
 *      函数的参数与返回值的说明请参见函数的注释。
 */
public class Practice_02 {
    public static void main(String[] args) {
        // 法一 ： pattern匹配
        if (Pattern.matches(".*.txt","str.txt")){
            System.out.println("kexing");
        }
        // 匹配2： 用字符串分隔
        if ("str.txt".split("\\.")["str.txt".split("\\.").length-1].equals("txt")){
            System.out.println("kexing");
        }
        // str.split("\\.")[str.split("\\.").length-1].equals("txt")
        System.out.println("*rule".toCharArray()[0]);
        System.out.println("d1dad".charAt(1) - '0');

    }
    public static boolean isRegularMatching(String rule, String str) {
        int lRule = rule.length();
        int lStr = str.length();
        int iRule = 0;
        int iStr = 0;
        while (iRule < lRule && iStr < lStr) {
            // 这里对规则 进行判断？
            switch (rule.toCharArray()[iRule]){
                case '*': {
                    iRule += 1;
                    // 如果 初始的规则 长度 >= 传过来的规则 长度
                    if (iRule >= lRule) {
                        return true;
                    } else {
                        // iRule < lRule 遍历这个str 字符串 长度
                        for (int i = iStr; i < lStr; i++) {
                            // -------(2)------
                            if (lStr > iStr){
                                return true;
                            }
                        }
                    }
                    break;
                }
                case '$': {
                    // _____(3) _____
                    if (lStr <= iStr){
                        return false;
                    }
                    while ((iStr < lStr) && (str.charAt(iStr) >= '0') && (str.charAt(iStr) <= '9')) {
                        iStr += 1;
                    }
                    iRule += 1;
                    break;
                }
                default: {
                    if (rule.charAt(iRule) != str.charAt(iStr)) {
                        //_____(4) _____;
                        return false;
                    }
                    iRule += 1;
                    iStr += 1;
                    break;
                }
            }
        }
        if (iRule < lRule && iStr >= lStr) {
            if (rule.charAt(iRule) == '*') {
                return true;
            }
        } else {
            // _____(5) _____;
            return iStr < lStr;
        }
        return false;
    }

}
