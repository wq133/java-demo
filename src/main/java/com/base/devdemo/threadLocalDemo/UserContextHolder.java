package com.base.devdemo.threadLocalDemo;

public class UserContextHolder {
          //创建ThreadLocal保存User对象
          public static ThreadLocal<User> holder = new ThreadLocal<>();

}
