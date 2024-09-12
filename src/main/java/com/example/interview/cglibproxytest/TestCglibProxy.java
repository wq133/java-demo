package com.example.interview.cglibproxytest;

public class TestCglibProxy {
          public static void main(String[] args) {
                    AliSmsService proxy = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
                    proxy.send("cglib test");
          }
}
