package com.lx.java8.lambda_lx;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * FileName: TestLambda3.java
 * Author:   lx
 * Date:     2018-07-21 16:51
 * Description:
 */
public class TestLambda3 {

    List<Employee> listOrigin = Arrays.asList(
            new Employee("李某", 50, 6666.66),
            new Employee("刘某", 25, 3333.33),
            new Employee("张某", 21, 5555.66),
            new Employee("王某", 56, 2222.66),
            new Employee("高某", 18, 8888.66),
            new Employee("黄某", 29, 1111.66),
            new Employee("佟某", 29, 3333.66)
    );

    /**
     * 排序，根据年龄排序，年龄一样的根据姓名排序
     *
     */
    @Test
    public void test() {
        Collections.sort(listOrigin, (x, y) -> {
            if (x.getAge().equals(y.getAge())) {
                return - Double.compare(x.getSalary(), y.getSalary());
            } else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        });

        listOrigin.forEach(System.out::println);
    }

    /**
     * 函数事接口x小写 小写转大写
     *
     */
    @Test
    public void test1() {

        System.out.println(turnUpperCase("dfdsfsdfsddf", e -> StringUtils.upperCase(e)));
    }

    String turnUpperCase(String str, MyStringTurn myStringTurn) {
        return myStringTurn.turnToUpperCase(str);
    }

    /**
     * 声明一个带两个泛型的函数式接口，泛型为<T, R> T为参数，R为返回值
     * 在接口中声明抽象方法
     * 在TestLambda类中声明方法，使用接口作为参数，计算两个Long型参数的和
     * 再计算两个Long型参数的乘积
     */
    @Test
    public void test2() {

        longOperation(11L, 12L, (x, y) -> "这是相加的结果:" + (x + y));

        longOperation(11L, 12L, (x, y) -> "这是相乘的结果:" + (x * y));

    }

    void longOperation(Long l1, Long l2, MyLongOperation<Long, String> m) {
        System.out.println(m.getValue(l1, l2));
    }
}
