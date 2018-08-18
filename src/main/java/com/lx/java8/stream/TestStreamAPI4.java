package com.lx.java8.stream;

import com.lx.java8.lambda_lx.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * FileName: TestStreamAPI4.java
 * Author:   lx
 * Date:     2018-08-10 8:16
 * Description: stream排序
 */
public class TestStreamAPI4 {

    List<Employee> employees = Arrays.asList(
            new Employee("李某", 50, 6666.66),
            new Employee("刘某", 25, 3333.33),
            new Employee("张某", 25, 5555.66),
            new Employee("王某", 56, 2222.66),
            new Employee("高某", 18, 8888.66),
            new Employee("杨某", 29, 1111.66),
            new Employee("高某某", 29, 1111.66),
            new Employee("陆某", 29, 1111.66),
            new Employee("佟某", 29, 1111.66)
    );

    /**
     * 排序
     * sorted()--自然排序（Comparable）
     * sorted(Comparator com)--定制排序（Comparator）
     */

    @Test
    public void test() {
        List<String> list = Arrays.asList("bbb", "ccc", "zzz", "kkk", "ggg");

        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("----------------------------");

        employees.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge().equals(e2.getAge())) {
                        if (e1.getSalary().equals(e2.getSalary())) {
                            return e1.getName().compareTo(e2.getName());
                        } else {
                            return e1.getSalary().compareTo(e2.getSalary());
                        }
                    } else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }


}
