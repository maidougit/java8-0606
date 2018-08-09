package com.lx.java8.stream;

import com.lx.java8.lambda_lx.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * FileName: TestStreamAPI2.java
 * Author:   lx
 * Date:     2018-08-02 12:22
 * Description: Stream流
 * <p>
 * 一、Stream 的三个操作步骤
 * <p>
 * 1、创建Stream
 * <p>
 * 2、中间操作
 * <p>
 * 3、终止操作（终端操作）
 */
public class TestStreamAPI2 {
    // 中间操作
    /**
     * 筛选与切片
     * <p>
     * filter--接收Lambda，从流中排除某些元素
     * limit--截断流，使其元素不超过给定数量
     * skip（n）--跳过元素，返回一个扔掉了前n个元素的流。如果流总元素不足n个，则返回一个空流。与limit(n)互补
     * distinct--筛选，通过流所生成的元素的hashCode()和equals()去除重复元素
     */
    List<Employee> listOrigin = Arrays.asList(
            new Employee("李某", 50, 6666.66),
            new Employee("刘某", 25, 3333.33),
            new Employee("张某", 21, 5555.66),
            new Employee("王某", 56, 2222.66),
            new Employee("高某", 18, 8888.66),
            new Employee("黄某", 29, 1111.66),
            new Employee("黄某", 29, 1111.66),
            new Employee("黄某", 29, 1111.66),
            new Employee("黄某", 29, 1111.66)
    );


    // 内部迭代：迭代操作由Stream API 完成
    @Test
    public void test1() {
        // 中间操作：不会执行任何操作
        Stream<Employee> stream = listOrigin.stream().filter(e -> {
            System.out.println("666");
            return e.getAge() > 30;
        });

        // 终止操作：一次性执行全部内容，即“惰性求值”
        stream.forEach(System.out::println);

    }

    // 外部迭代
    @Test
    public void test2() {
        Iterator<Employee> iterator = listOrigin.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test3() {
        listOrigin.stream()
                .filter(e -> {
                    System.out.println("短路？");
                    return e.getAge() > 25;
                })
                .limit(2) // 找到指定数量的元素后，就会终止迭代操作，不再进行后续元素的迭代
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        listOrigin.stream()
                .filter(e -> {
                    System.out.println("短路");
                    return e.getAge() > 25;
                })
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

}
