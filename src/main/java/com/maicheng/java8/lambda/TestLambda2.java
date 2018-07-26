package com.maicheng.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 描述:TODO
 *
 * FileName: TestLambda2.java
 * @Author: maido
 * @date:    2018/7/25
 * @since v1.8.0
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