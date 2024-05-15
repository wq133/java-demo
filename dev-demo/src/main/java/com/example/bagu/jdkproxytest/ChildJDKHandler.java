package com.example.bagu.jdkproxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ChildJDKHandler implements InvocationHandler {

          private final Object target;

          public ChildJDKHandler(Object target) {
                    this.target = target;
          }

          /*
          proxy :动态生成的代理类
          method : 与代理类对象调用的方法相对应
          args : 当前 method 方法的参数
           */
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 调用方法之前 我们同样可以添加自己的操作
                    System.out.println("before invocation");

                    Object result = method.invoke(target, args);

                    // 调用方法之后 我们同样可以添加自己的操作
                    System.out.println("After invocation");

                    return result;
          }



}
