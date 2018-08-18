package com.interview;

import cn.hutool.core.lang.Console;

/**
 * 描述:面试题
 *
 * FileName: TestInterview.java
 * @Author: maido
 * @date:    2018/8/13
 * @since v1.8.0
 */
public class TestInterview {

    static int cnt = 0;


    static int fib(int n) {
        Console.log("监听结果：{}", n);
        cnt++;
        if (n == 0) return 1;
        else if (n == 1) return 2;
        else return fib(n -1) + fib(n-2);
    }


    public static void main(String[] args) {
        fib(2);

        Console.log("结果：{}", cnt);
    }
}