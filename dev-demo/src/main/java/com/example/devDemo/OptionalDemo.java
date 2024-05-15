package com.example.devDemo;

import com.example.devDemo.entity.Role;
import com.example.devDemo.entity.Student;
import com.example.devDemo.utils.GenerateUUID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        List<Object> arrayList = null;


        // 创建一个空的 Optional 对象
        Optional<String> emptyOptional = Optional.empty();

        // 创建包含值的 Optional 对象
        Optional<String> nonEmptyOptional = Optional.of("Hello");

        // 创建包含可能为空的值的 Optional 对象
        List<Object> objects = Optional.ofNullable(arrayList).orElse(new ArrayList<>());
        System.out.println("objects = " + objects);

        //isPresent()：检查值是否存在。
        boolean present = emptyOptional.isPresent();
        System.out.println("present = " + present);


        //get()：获取值，如果值不存在会抛出 NoSuchElementException 异常。
        String getValue = nonEmptyOptional.get();
        System.out.println("getValue = " + getValue);


        //orElse(T other)：获取值，如果值不存在则返回指定的默认值。
        String tmd = emptyOptional.orElse("tmd");
        System.out.println("tmd = " + tmd);


        //orElseGet(Supplier<? extends T> other)：获取值，如果值不存在则返回由 Supplier 提供的默认值。
        Optional<String> nullableOptional = Optional.ofNullable(null);
        System.out.println(nullableOptional.orElse("defaultString。。。"));

        String elseGet = nullableOptional.orElseGet(() -> {
            System.out.println("执行 defaultStr 方法 orElseGet");
            return "defaultStr";
        });
        System.out.println("elseGet = " + elseGet);

        nullableOptional = Optional.of("yyfw");
        String elseGetValue = nullableOptional.orElseGet(() -> {
            System.out.println("执行 defaultStr 方法 orElseGet");
            return "defaultStr";
        });
        System.out.println("elseGetValue = " + elseGetValue);

        //orElseThrow(Supplier<? extends X> exceptionSupplier)：获取值，如果值不存在则抛出由 Supplier 提供的异常。

        // 使用 orElseThrow(Supplier<? extends X> exceptionSupplier)
        Optional<String> name = Optional.ofNullable(null);
//        String greeting = "Hello, " + name.orElseThrow(() -> new BusinessException("Optionl 抛出一个empty Error！"));
        // 抛出 java.lang.NullPointerException: null 异常
//        System.out.println("greeting = " + greeting);
        
        /*
            map、flatMap
            <U> Optional<U> map(Function<? super T, ? extends U> mapper) ：如果有值，则进行数据处理，如果没有值，则返回Optional.empty()。
            <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) ：与map类似，要求返回值必须是Optional。
        */


        Student student1 = Student.builder()
                .id(GenerateUUID.generate())
                .age(18)
                .name(null)
                .role(Role.normal)
                .build();
        Student student2 = Student.builder()
                .id(GenerateUUID.generate())
                .age(18)
                .name("赵烬彦2")
                .build();
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        //map
        String studentName = Optional.of(student2).map(Student::getName).get();
        System.out.println("student2Name：" + studentName);

        Optional<String> optionalStudentName = Optional.ofNullable(student1)
                .map(Student::getName);
        System.out.println("optionalStudentName：" + optionalStudentName.equals(emptyOptional));


        //flatMap
        String description = Optional.of(student1)
                .flatMap(Student::optionalRole)
                .map(Role::getDescription)
                .get();
        System.out.println("description：" + description);
    }
}

