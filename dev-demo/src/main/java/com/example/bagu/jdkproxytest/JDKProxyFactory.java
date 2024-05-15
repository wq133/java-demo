package com.example.bagu.jdkproxytest;

import java.lang.reflect.Proxy;

public class JDKProxyFactory {

          public static Object getProxy(Object target) {
                    return Proxy.newProxyInstance(target.getClass().getClassLoader(), // 目标类的类加载器
                            target.getClass().getInterfaces(), // 代理需要实现的接口，可指定多个
                            new ChildJDKHandler(target)); // 代理对象对应的自定义 InvocationHandler
          }
}