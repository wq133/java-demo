package com.example.devdemo.atomicdemo;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 如果需要原子更新某个类里的某个字段时，需要用到对象的属性修改类型原子类。
 * AtomicIntegerFieldUpdater:原子更新整形字段的更新器
 * AtomicLongFieldUpdater：原子更新长整形字段的更新器
 * AtomicReferenceFieldUpdater：原子更新引用类型里的字段的更新器要想原子地更新对象的属性需要两步。
 * 第一步，因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法 newUpdater()创建一个更新器，并且需要设置想要更新的类和属性。第二步，更新的对象属性必须使用 public volatile 修饰符。
 * ------
 */
public class AtomicIntegerFieldUpdaterTest {
          public static void main(String[] args) {
                    // Person 类


// 创建 AtomicIntegerFieldUpdater 对象
                    AtomicIntegerFieldUpdater<Person> ageUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");

// 创建 Person 对象
                    Person person = new Person("SnailClimb", 22);

// 打印初始值
                    System.out.println("Initial Person: " + person);

// 更新 age 字段
                    ageUpdater.incrementAndGet(person); // 自增
                    System.out.println("After Increment: " + person);

                    ageUpdater.addAndGet(person, 5); // 增加 5
                    System.out.println("After Adding 5: " + person);

                    ageUpdater.compareAndSet(person, 28, 30); // 如果当前值是 28，则设置为 30
                    System.out.println("After Compare and Set (28 to 30): " + person);

// 尝试使用错误的比较值进行更新
                    boolean isUpdated = ageUpdater.compareAndSet(person, 28, 35); // 这次应该失败
                    System.out.println("Compare and Set (28 to 35) Success: " + isUpdated);
                    System.out.println("Final Person: " + person);

          }
}
