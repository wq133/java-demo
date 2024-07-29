package com.example.bagu;

public class equalsDemo {

          public static void main(String[] args) {
                    String str1 = "abc";
                    String str2 = new String("abc");
                    String str3 = "abc";
                    String str4 =  "xxx";
                    String str5 = "abc" + "xxx";

                    String s3 = "cbd";
                    String s4 = "cbd";
                    String str6 = s3 + s4;

                    // == ：基本数据类型比较的是值 引用数据类型比较的是地址
                    // equals： 未重写相当于==  重写 比较的是值
                    System.out.println("str1 == str2：" + (str1 == str2));
                    // true -》
                    // 正确：false == String是引用类型，比较的是地址
                    // 因为它们分别指向字符串池中的常量字符串和堆内存中新创建的字符串对象，这两个对象在内存中的地址是不同的。


                    System.out.println(str1);
                    System.out.println(str2);

                    System.out.println("str1.equals(str2)：" + (str1.equals(str2)));// true
                    // ⬆️Object的equals是原生的未重写的（比较的是对象中的内存地址），
                    // String中的是重写过的，所以String的equals比较的是值
                    // 但是这里str1 是常量池中abc的值。 str2值是对象的

                    System.out.println("str1 == str5：" + (str1 == str5)); // false
                    System.out.println("str1 == str6：" + (str1 == str6)); // false
                    System.out.println("str5 == str6：" + (str5 == str6)); // true -》false 两个不同的object
                    System.out.println("str5.equals(str6)：" + (str5.equals(str6))); // true -》false 同上

                    //str.intern() 是 Java 中的一个方法，它的作用是返回字符串对象的规范化表示形式。
                    // 具体来说，如果字符串常量池中已经包含一个等于此String对象的字符串（由 equals 方法确定），
                    // 则返回常量池中对应字符串的引用；
                    // 否则，将该String对象添加到常量池中，并返回该String对象的引用。

                    //        使用 str.intern() 主要是为了在字符串比较时
                    //        ，使用 == 进行引用比较，而不是 equals 方法进行内容比较，以提高比较效率。
                    System.out.println("str1 == str6.intern()：" + (str1 == str6.intern())); //false
                    System.out.println("str1 == str2.intern()：" + (str1 == str2.intern())); //true

          }
}
