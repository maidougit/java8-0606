package com.lx.java8.stream;

import com.lx.java8.lambda_lx.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * FileName: TestStreamAPI5.java
 * Author:   lx
 * Date:     2018-08-10 8:35
 * Description: stream终止操作
 */
public class TestStreamAPI5 {
    /**
     * 终止操作：
     *
     * 查找与匹配
     * allMatch--检查是否匹配所有元素
     * anyMatch--检查是否至少匹配一个元素
     * noneMatch--检查是否没有匹配所有元素
     * findFirst--返回第一个元素
     * findAny--返回当前流中的任意元素
     * count--返回流中元素的总个数
     * max--返回流中的最大值
     * min--返回流中的最小值
     *
     *
     *
     *
     */

    List<Employee> employees = Arrays.asList(
            new Employee("李某", 50, 6666.66, Employee.Status.BUSY),
            new Employee("刘某", 25, 3333.33, Employee.Status.FREE),
            new Employee("张某", 25, 5555.66, Employee.Status.VOCATION),
            new Employee("王某", 56, 2222.66, Employee.Status.FREE),
            new Employee("高某", 18, 8888.66, Employee.Status.FREE),
            new Employee("杨某", 29, 1111.66, Employee.Status.BUSY),
            new Employee("高某某", 29, 1111.66, Employee.Status.VOCATION),
            new Employee("陆某", 29, 1111.66, Employee.Status.VOCATION),
            new Employee("佟某", 29, 1111.66, Employee.Status.BUSY)
    );

    @Test
    public void test() {
        System.out.println("--------------是否全部匹配");

        boolean match = employees.stream().allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(match);

        System.out.println("--------------");

        boolean match1 = employees.stream().allMatch(e -> e.getAge() > 1);
        System.out.println(match1);

        System.out.println("--------------是否至少匹配一个");

        boolean match2 = employees.stream().anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(match2);

        System.out.println("--------------是否全部不匹配");

        boolean match3 = employees.stream().noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(match3);

        System.out.println("--------------");

        boolean match4 = employees.stream().noneMatch(e -> e.getStatus().equals(Employee.Status.FIRED));
        System.out.println(match4);

        System.out.println("--------------返回第一个");
        Optional<Employee> first = employees.stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())).findFirst();
        System.out.println(first.get());

        System.out.println("--------------返回任意一个");
        Optional<Employee> any = employees.stream().filter(e -> e.getStatus().equals(Employee.Status.BUSY)).findAny();
        System.out.println(any.get());
    }

    @Test
    public void test2() {
        System.out.println("--------------返回计数");
        Long count = employees.stream().filter(e -> !e.getStatus().equals(Employee.Status.BUSY)).count();
        System.out.println(count);

        System.out.println("--------------返回最大");
        Optional<Employee> max = employees.stream().max((e1, e2) -> e1.getSalary().compareTo(e2.getSalary()));
        System.out.println(max.get());

        System.out.println("--------------返回最小");
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min.get());


    }

    @Test
    public void test3() {
        String str = "www.666.com,w";
        String[] split = str.split(",");
        Arrays.stream(split).forEach(System.out::println);
    }

}
