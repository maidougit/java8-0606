package com.maicheng.java8.localdate;

import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Copyright (C), 2017-2018, maicheng
 * FileName: TestLocalDateTime.java
 * Author:   maido
 * Date:     2018/7/24 7:10
 * Description:
 */
public class TestLocalDateTime {

    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("当前时间：" + localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(2106, 2, 11, 12,12,12);
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = localDateTime1.plusYears(2);

        System.out.println(localDateTime2);
    }
}