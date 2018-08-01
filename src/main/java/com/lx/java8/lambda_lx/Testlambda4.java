package com.lx.java8.lambda_lx;

/*
 * Java8内置四大核心函数式接口
 * Consumer<T>：消费型接口
 *     void accept(T t);
 *
 * Supplier<T>：供给型接口
 *     T get();
 *
 * Function<T, R>：函数型接口
 *     R apply(T t);
 *
 * Predicate<T>：断言型接口
 *     boolean test(T t);
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * FileName: Testlambda4.java
 * Author:   lx
 * Date:     2018-07-23 12:21
 * Description: Java8内置四大核心函数式接口
 */
public class Testlambda4 {


    // 需求：花钱 用消费型接口
    public void beHappy(Double d, Consumer<Double> con) {
        con.accept(d);
    }

    // 消费型接口Consumer<T>
    @Test
    public void test1() {
        beHappy(1000.1, m -> {
            System.out.println("黄大神日理万机，每天消费：" + m + "元");
            System.out.println("消费" + m + "元后，感觉不够");
            System.out.println("又来了" + (m * m) + "元的");
        });
    }


    // 需求：产生指定个数的整数，并放入集合中，用供给型接口
    public List<Integer> getNumList(int m, Supplier<Integer> s) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(s.get());
        }
        return list;
    }

    /**
     * Supplier<T>：供给型接口
     * T get();
     */
    @Test
    public void test2() {
        List<Integer> list = getNumList(15, () -> (int) (Math.random() * 100));
        list.forEach(System.out::println);
    }

    // 需求：处理一个字符串：用函数式接口
    private String strhandler(String str, Function<String, String> f) {
        return f.apply(str);
    }


    /**
     * Function<T, R>：函数型接口
     * R apply(T t);
     */
    @Test
    public void test3() {
        String trimStr = strhandler("\t\t\t\t哈哈哈哈哈asadfa   ", (t) -> t.trim().toUpperCase());
        System.out.println(trimStr);
    }

    // 将符合条件的字符串，放入集合中去；用断言型接口
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();
        for (String str : list) {
            if (pre.test(str)) {
                strList.add(str);
            }
        }

        return strList;
    }

    @Test
    public void test4() {
        List<String> list = Arrays.asList("hello", "how", "what", "get");
        List<String> listNew = filterStr(list, str -> str.contains("h"));
        listNew.stream().forEach(System.out::println);
    }

}
