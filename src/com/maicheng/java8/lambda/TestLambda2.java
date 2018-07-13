package com.maicheng.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Copyright (C), 2017-2018, 上海阁为信息科技有限公司
 * FileName: TestLambda2.java
 * Author:   hyz
 * Date:     2018-06-20 23:25
 * Description: lambda 中引入了一个新的操作符，高操作符成为箭头操作符
 */
public class TestLambda2 {

    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hi sim");
            }
        };

        r1.run();

        System.out.println("------------------->");

        Runnable r2 = () -> System.out.println("hi java");
        r2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> result = (x, y) -> Integer.compare(x, y);

        System.out.println("比较结果：" + result.compare(1, 2));
    }

    @Test
    public void test3() {
        Consumer<String> result = x -> System.out.println(x);

        result.accept("i am maidou");
    }
}