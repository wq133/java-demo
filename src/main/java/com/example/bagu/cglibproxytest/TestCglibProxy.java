package com.example.bagu.cglibproxytest;

public class TestCglibProxy {
          public static void main(String[] args) {
                    AliSmsService proxy = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
                    proxy.send("cglib test");
          }
}
