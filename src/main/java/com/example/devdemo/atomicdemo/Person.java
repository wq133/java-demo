package com.example.devdemo.atomicdemo;

class Person {
          private String name;
          private int age;

          public Person(String name, int age) {
                    this.name = name;
                    this.age = age;
          }

          //省略getter/setter和toString
          @Override
          public String toString() {
                    return "Person: " + name + " " + age;
          }
}