package com.example.bagu.cglibproxytest;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Objects;

public class MyInterceptor implements MethodInterceptor {

          /*
          obj : 被代理的对象（需要增强的对象）
          method : 被拦截的方法（需要增强的方法）
          args : 方法入参
          proxy : 用于调用原始方法
           */
          @Override
          public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    //调用方法之前，我们可以添加自己的操作
                    System.out.println("Before method execution,Interceptor do");

                    // 执行目标方法
                    Object object = methodProxy.invokeSuper(o, objects);
                    //调用方法之后，我们可以添加自己的操作

                    System.out.println("After method execution,Interceptor do");

                    return object;
          }
}