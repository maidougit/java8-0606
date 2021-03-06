package com.maicheng.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 描述:TODO
 *
 * FileName: TestLambda3.java
 * @Author: maido
 * @date:    2018/7/25
 * @since v1.8.0
 */
public class TestLambda3 {

    //Consumer<T> 消费性接口
    @Test
    public void  test1() {

        happy(1000, (m) -> System.out.println("青春无悔" + m + "元"));
    }

    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));

        for (Integer result:numList) {
            System.out.println(result);
        }
    }

    @Test
    public void test3() {

        String str1 = getStr("\t\t\t我是青春无悔", (str) -> str.trim());

        System.out.println("--->" + str1);
    }

    private void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

    private List<Integer> getNumList(Integer num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i< num; i++) {

            list.add(sup.get());
        }

        return list;
    }

    public String getStr(String result, Function<String, String> fun) {

        return fun.apply(result);
    }
}