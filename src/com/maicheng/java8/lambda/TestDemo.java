package com.maicheng.java8.lambda;

/**
 * 方法的功能描述:java 测试Demo
 *
 * @Author: maido
 * @date:    2018-07-08 09:42-9:42
 * @param
 * @since v1.8.0
 */

interface  IMessage{
    void print();
}
public class TestDemo {

    public static void fun(IMessage message) {

        message.print();
    }

    public static void main(String[] args) {
        // 匿名内部类 抽象和接口
        fun(new IMessage() {
            @Override
            public void print() {

                System.out.println("我是匿名内部类");
            }
        });

        // 避免内部类定义过多的无用操作
        fun(() -> System.out.println("我是lambda"));
    }
}