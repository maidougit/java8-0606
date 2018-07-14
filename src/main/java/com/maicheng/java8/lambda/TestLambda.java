package com.maicheng.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;


/**
 * Copyright (C), 2017-2018, 上海阁为信息科技有限公司
 * FileName: TestLambda.java
 * Author:   hyz
 * Date:     2018-06-20 22:38
 * Description:
 */
public class TestLambda {

    @Test
    public void test1() {

        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    public void test2() {
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);

        TreeSet<Integer> ts = new TreeSet<>(com);
    }
}