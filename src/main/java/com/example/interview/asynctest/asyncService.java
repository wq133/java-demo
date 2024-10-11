package com.example.interview.asynctest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * async demo
 */
@Slf4j
@Component
public class asyncService {

    @Autowired
    private Test test;

    @Autowired
    private Test1 test1;

    public void asyncService() {

        log.info("主线程名:{}" + Thread.currentThread().getName());
        log.info("1st:调用当前类中带有@Async注解的方法...");
        this.testAsync();
        log.info("当前类名:{}", this.getClass().getName());
        log.info("===========================================");
        log.info("2nd:调用其他类Test类中带有@Async注解的方法...");
        test.testAsync();
        Class c = test.getClass();
        log.info("当前类名:{}", c.getName());
        log.info("Test类的父类:{}", c.getSuperclass().getName());
        log.info("===========================================");
        log.info("3rd:调用其他类Test1类不带@Async注解的方法...");
        test1.test();
        log.info("当前类名:{}", test1.getClass().getName());
        log.info("===========================================");
        log.info("4th:调用其他类Test2类（没有被springboot初始化的类）中带有Async注解的方法...");
        Test2 test2 = new Test2();
        test2.testAsync();
        log.info("当前类名:{}", test2.getClass().getName());
    }

    @Async
    public void testAsync() {
        log.info("当前类调用线程名：" + Thread.currentThread().getName());
    }
}


@Slf4j
@Component
class Test {
    @Async
    public void testAsync() {
        log.info("Test类线程名：" + Thread.currentThread().getName());
    }

    public void method() {
        log.info("this is Test.method");
    }
}


@Slf4j
@Component
class Test1 {
    public void test() {
        log.info("Test1类线程名：" + Thread.currentThread().getName());
    }
}


@Slf4j
class Test2 {
    public void testAsync() {
        log.info("Test2类线程名：" + Thread.currentThread().getName());
    }
}

