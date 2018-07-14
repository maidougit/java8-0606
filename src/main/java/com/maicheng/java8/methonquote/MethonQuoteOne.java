package com.maicheng.java8.methonquote;

import com.maicheng.java8.predicate.Apple;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Copyright (C), 2017-2018, maicheng
 * FileName: MethonQuoteOne.java
 * Author:   hyz
 * Date:     2018-06-13 17:56
 * Description:指向静态方法的方法引用(例如Integer的parseInt方法，写作Integer::parseInt)；
 *   指向任意类型实例方法的方法引用(例如String的length方法，写作String::length)；
 *  指向现有对象的实例方法的方法引用(例如假设你有一个本地变量localVariable用于存放Variable类型的对象，
 *  它支持实例方法getValue，那么可以写成localVariable::getValue)。
 */
public class MethonQuoteOne {

    public static void main(String[] args) {
        Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);

        //使用方法引用
        Function<String, Integer> stringToInteger1 = Integer::parseInt;

        //方法引用中还有一种特殊的形式，构造函数引用，假设一个类有一个默认的构造函数，那么使用方法引用的形式为：
        Supplier<Apple> c1 = Apple::new;
        Apple s1 = c1.get();
        //等价于
        Supplier<Apple> c2 = () -> new Apple();
        Apple s2 = c1.get();

        //如果是构造函数有一个参数的情况：
        Function<Integer, Apple> c3 = Apple::new;
        Apple s3 = c3.apply(100);

         //等价于

        Function<Integer, Apple> c4 = i -> new Apple(100);
        Apple s4 = c4.apply(200);

        System.out.println("重量结果:--->" + s4.getGetWeight());
    }
}