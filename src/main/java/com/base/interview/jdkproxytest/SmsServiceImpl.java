package com.base.interview.jdkproxytest;

public class SmsServiceImpl implements SmsService
{

          @Override
          public String send(String message) {
                    System.out.println("Send Message successful!");
                    return message;
          }

}
