package com.maicheng.java8.lambda;

import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Consumer;


/**
 * Copyright (C), 2017-2018, maicheng
 * FileName: TestLambda.java
 * Author:   maidou
 * Date:     2018-06-20 22:38
 * Description:  java8 新的语法 "->" 箭头操作符(lambda操作符)表达式拆分 :
 *  左侧 : 参数列表
 *  右侧 : 表达式执行的功能， lambda 体
 */
public class TestLambda {

   /**
    * 方法test1的功能描述: 语法格式三 有两个以上参数，有返回值
    *  for example  Comparator<Integer> compare = (x, y) -> x.compareTo(y)
    *  表达式中参数列表的参数类型可以不屑， JVM编译器通过上下文推出数据类型，即类型推断
    *
    * @Author: maido
    * @date:    2018-07-14 20:54
    * @return : void
    * @since v1.8.0
    */
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return Integer.compare(o1, o2);
            }
        };

        com.compare(3,1);

        Comparator<Integer> compare = (x, y) -> x.compareTo(y) ;
        Console.log("比较结果：{}", compare.compare(3,5));
    }

    /**
     * @Author: maido
     * @date:    2018-07-14 21:07
     * @param    []
     * @return : void
     * @since v1.8.0
     */
    @Test
    public void test2() {
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);

        TreeSet<Integer> ts = new TreeSet<>(com);

        ts.add(1);
        ts.add(3);
        ts.add(2);

        Console.log( "结果：{}" , ts.toArray());
    }

    /**
     * 方法test3的功能描述:语法格式一
     *  无参数，无返回值 () -> System.out.println("hellob world")
     *
     * @Author: maido
     * @date:    2018-07-14 21:16
     * @param    []
     * @return : void
     * @since v1.8.0
     */
    @Test
    public void test3() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Console.log("hello world");
            }
        };
        runnable.run();

        Runnable r1 = () ->  Console.log("hello lambda");
        r1.run();
    }

    /**
     * 方法test4的功能描述:语法格式二
     * 一个参数， 无返回值
     * @Author: maido
     * @date:    2018-07-14 21:19
     * @param    []
     * @return : void
     * @since v1.8.0
     */
    @Test
    public void test4() {
        Consumer<String> result = (x) -> Console.log("我是谁：{}", x);

        result.accept("maicheng");
    }

    /**
     * 方法test5的功能描述:TODO
     *
     * @Author: maido
     * @date:    2018-07-14 23:58 
     * @param 
     * @return : void
     * @since v1.8.0
     */
    @Test
    public void test5() {

    }
}