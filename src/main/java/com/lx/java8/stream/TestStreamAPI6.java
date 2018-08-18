package com.lx.java8.stream;

import com.lx.java8.lambda_lx.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * FileName: TestStreamAPI6.java
 * Author:   lx
 * Date:     2018-08-15 8:24
 * Description: 归约
 */
public class TestStreamAPI6 {
    /**
     * 终止操作：
     *
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator) --可以将流中的元素反复结合起来，得到一个值
     *
     * 收集
     * collect--将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     *
     */

    List<Employee> employees = Arrays.asList(
            new Employee("李某", 50, 6666.66, Employee.Status.BUSY),
            new Employee("刘某", 25, 3333.33, Employee.Status.FREE),
            new Employee("张某", 25, 5555.66, Employee.Status.VOCATION),
            new Employee("王某", 56, 2222.66, Employee.Status.FREE),
            new Employee("高某", 18, 8888.66, Employee.Status.FREE),
            new Employee("杨某", 29, 1111.66, Employee.Status.BUSY),
            new Employee("高某", 29, 1111.66, Employee.Status.VOCATION),
            new Employee("陆某", 29, 1111.66, Employee.Status.VOCATION),
            new Employee("佟某", 29, 1111.66, Employee.Status.BUSY)
    );


    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("---------------------工资的总和");
        Optional<Double> reduce = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce.get());

    }


    @Test
    public void test1() {

        System.out.println("----------------将名字放入list");
        List<String> list = employees.stream().map(Employee::getName).collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("----------------将名字放入set");
        Set<String> set = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("----------------将名字放入指定集合中");
        HashSet<String> hashSet = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);

    }

    @Test
    public void test2() {

    }

}
