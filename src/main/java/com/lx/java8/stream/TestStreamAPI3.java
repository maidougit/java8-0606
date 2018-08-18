package com.lx.java8.stream;

import com.lx.java8.lambda_lx.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * FileName: TestStreamAPI3.java
 * Author:   lx
 * Date:     2018-08-03 8:43
 * Description: 映射
 */
public class TestStreamAPI3 {

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

    /**
     * 映射
     * map——接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap--接收一个函数作为参数，将流中的每一个值都换成另一个流，然后把所有流连接成一个流
     *
     */
    @Test
    public void test() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "eee");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("--------");

        listOrigin.stream()
                .map(Employee::getSalary)
                .forEach(System.out::println);

        Stream<Stream<Character>> stream = list.stream().map(TestStreamAPI3::filterCharacter); // {{a, a, a}, {b, b, b}}
        stream.forEach(sm -> {
            sm.forEach(System.out::println);
        });

        System.out.println("--------");

        Stream<Character> sm2 = list.stream().flatMap(TestStreamAPI3::filterCharacter); // {a, a, a, b, b, b}
        sm2.forEach(System.out::println);


    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch :
             str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }


    @Test
    public void test6() {
        for (Employee employee : listOrigin) {
            employee.setName("干嘛都@我？");
        }

        listOrigin.forEach(System.out::println);
    }

}
