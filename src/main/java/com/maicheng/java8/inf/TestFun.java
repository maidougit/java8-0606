package com.maicheng.java8.inf;

import cn.hutool.core.lang.Console;
import com.maicheng.java8.model.Student;
import org.junit.Test;

/**
 * 描述:测试函数式接口
 *
 * FileName: TestFun.java
 * @Author: maido
 * @date:    2018/7/25
 * @since v1.8.0
 */
public class TestFun {

    @Test
    public void test1Fun() {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");

        Console.log("结果：{}", converted);
    }

    //静态方法引用
    @Test
    public void test2Fun() {
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        Console.log("结果：{}", converted);   // 123
    }

    //通过::关键字传递对方法或者构造器的引用
    @Test
    public void test3Fun() {
        Somethting somethting = new Somethting();
        Converter<String, String> converter =somethting::toUpperCase;
        String converted = converter.convert("Java");

        Console.log("结果：{}", converted);

    }

    //转换成大写
    class Somethting{
        String toUpperCase(String str) {

            return String.valueOf(str.toUpperCase());
        }
    }

    //::关键字是如何为构造器工作的
    @Test
    public void test4Fun() {
        StudentFactory factory = Student::new;
        Student student = factory.create("张三", 12);

        Console.log("学生信息：{}", student.toString());

    }
}