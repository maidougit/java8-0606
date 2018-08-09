package com.lx.java8.stream;

import com.lx.java8.lambda_lx.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * FileName: TestStreamAPI1.java
 * Author:   lx
 * Date:     2018-08-02 7:58
 * Description: stream流
 *
 * 一、Stream 的三个操作步骤
 *
 * 1、创建Stream
 *
 * 2、中间操作
 *
 * 3、终止操作（终端操作）
 *
 *
 */
public class TestStreamAPI1 {

    // 创建stream
    @Test
    public void test1() {
        // 1 可以通过Collection系列集合提供的stream() （串行流） 或 parallelStream() （并行流）创建
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2 通过Arrays中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        // 3 通过Stream类中的静态方法 of()
        Stream<String> stream2 = Stream.of("qq", "dd", "bb");

        // 4 创建无限流
        // 迭代
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2);
        stream3.limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
        Stream.generate(Math::random).limit(5).forEach(System.out::println);


    }


    public static boolean isNumeric(CharSequence cs) {
        if(isEmpty(cs)) {
            return false;
        } else {
            int sz = cs.length();

            for(int i = 0; i < sz; ++i) {
                if(!Character.isDigit(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }


    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
