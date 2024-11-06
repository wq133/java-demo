package com.base.datastructure.cs61b.unit1;

/**
 * 基本特性
 */
public class demo01 {


    // 风格指南
    public static void main(String[] args) {

        // Java 最重要的特性之一是所有变量和表达式都有一个所谓的static type。Java 变量可以包含该类型的值，
       // 并且只能包含该类型的值。此外，变量的类型永远不会改变。

        // java是一个强类型语言 不像python是一个动态类型的


        // 编译器确保所有类型兼容，使程序员更容易调试他们的代码。
        //由于代码保证没有类型错误，因此编译程序的用户永远不会遇到类型错误。例如，Android 应用程序是用 Java 编写的，通常仅以 .class 文件（即编译格式）分发。因此，此类应用程序永远不会因类型错误而崩溃，因为它们已经过编译器检查。
        //每个变量、参数和函数都有声明的类型，这使得程序员更容易理解和推理代码。

        //静态类型也有几个缺点，我们将在后面的章节中进一步讨论。举几个例子：- 更详细的代码。- 通用性较差的代码。


//        int x = 0;
//        while (x < 10) {
//            System.out.print(x + " ");
//            x = x + 1;
//        }
//        x = "horse";






    }

}
// 自定义一个函数
class LargerDemo {
    /** Returns the larger of x and y. */
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
}