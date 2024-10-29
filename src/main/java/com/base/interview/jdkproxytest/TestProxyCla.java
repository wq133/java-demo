package com.base.interview.jdkproxytest;

public class TestProxyCla {
          public static void main(String[] args) {
                    SmsService smsService =(SmsService) JDKProxyFactory.getProxy(new SmsServiceImpl());
                    smsService.send("test");




          }

}
