package com.maicheng.java8.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Copyright (C), 2017-2018, 上海阁为信息科技有限公司
 * FileName: TestLambdaRef.java
 * Author:   hyz
 * Date:     2018-06-21 23:53
 * Description:
 */
public class TestLambdaRef {

    //对象::实例方法名
    @Test
    public void test1() {
        Consumer<String> con = (x) -> System.out.println("x");

        PrintStream printStream = System.out;

        Consumer<String> con1 = printStream::println;

        Consumer<String> con2 = System.out::println;

        con2.accept("结果");
    }

    @Test
    public void test2() {
        Supplier<String> supplier = () -> {
            return "我是结果";
        };

        supplier.get();

        Supplier<String> supplier1 = String::new;

        supplier1.get();
    }

    //类::静态方法
    @Test
    public void test3() {
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        com.compare(1,2);

        Comparator<Integer> com1 = Integer::compare;
        com1.compare(2,3);
    }

    // 类::实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        BiPredicate<String, String> biPredicate1 = String::equals;
    }

    //构造器引用
    @Test
    public void test5() {
        Supplier<String> supplier = String::new;

        supplier.get();
    }
}