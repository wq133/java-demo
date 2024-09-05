package com.example.devDemo.stringdemo;

public class StringDemo {

    public static void main(String[] args) {
        String contentTemplate = "你好，手机尾号为%s的顾客，您的验证机码为%s，请于5分钟之内使用";
        String mobile = "13343710600";
        String verifyCode = "";
        String smsContent = String.format(contentTemplate, mobile, verifyCode);
        System.out.println("smsContent = " + smsContent);
        String urlTemplate = "https://www.baidu.com?branchCode=%s&code=%s&name=%s";
        String branchCode = "3010100";
        String code = "why002";
        String name = "旺旺旺";
        String urlSendContent = String.format(urlTemplate, branchCode, code, name);
        System.out.println("urlSendContent = " + urlSendContent);

    }

    /**
     * java 字符串 转换符
     *
     * 参考链接     https://blog.csdn.net/daobuxinzi/article/details/117665032
     *
     * @param args
     */
    public static void main1(String[] args) {
            String str=null;
            str=String.format("Hi,%s", "王力");
            System.out.println(str);
            str=String.format("Hi,%s:%s.%s", "王南","王力","王张");
            System.out.println(str);
            System.out.printf("字母a的大写是：%c %n", 'A');
            System.out.printf("3>7的结果是：%b %n", 3>7);
            System.out.printf("100的一半是：%d %n", 100/2);
            System.out.printf("100的16进制数是：%x %n", 100);
            System.out.printf("100的8进制数是：%o %n", 100);
            System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);
            System.out.printf("上面价格的16进制数是：%a %n", 50*0.85);
            System.out.printf("上面价格的指数表示：%e %n", 50*0.85);
            System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50*0.85);
            System.out.printf("上面的折扣是%d%% %n", 85);
            System.out.printf("字母A的散列码是：%h %n", 'A');
    }


}
