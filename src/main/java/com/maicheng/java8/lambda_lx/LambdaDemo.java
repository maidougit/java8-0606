package com.maicheng.java8.lambda_lx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2017-2018, 上海阁为信息科技有限公司
 * FileName: LambdaDemo.java
 * Author:   lx
 * Date:     2018-07-21 11:40
 * Description: lambda表达式
 */
public class LambdaDemo {

    List<Employee> listOrigin = Arrays.asList(
            new Employee("李某", 50, 6666.66),
            new Employee("刘某", 25, 3333.33),
            new Employee("张某", 21, 5555.66),
            new Employee("王某", 56, 2222.66),
            new Employee("高某", 18, 8888.66),
            new Employee("黄某", 29, 1111.66)
    );

    List<Employee> getListWithFilter(List<Employee> list, MyPredicate<Employee> myPredicate) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (myPredicate.test(employee)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    // 传统的匿名内部类
    @Test
    public void test1() {
        // 获取年龄大于25的员工
        List<Employee> listNew = getListWithFilter(listOrigin, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 25;
            }
        });

        for (Employee employee :
                listNew) {
            System.out.println(employee);
        }
    }

    // Lambda函数
    @Test
    public void test2() {
        List<Employee> list = getListWithFilter(listOrigin, (e) -> e.getAge() > 25);
        list.forEach(System.out::println);
    }

    // lambda函数+stream API
    @Test
    public void test3() {
        listOrigin.stream()
                .filter((e) -> e.getAge() > 25)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("-------------------------------------------");
        listOrigin.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
