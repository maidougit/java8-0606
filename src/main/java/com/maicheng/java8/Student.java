package com.maicheng.java8;

import cn.hutool.core.lang.Console;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Student {

    private String name;

    private Integer age;

    Student(String name, int age) {
        Console.log("(默认)的构造方法 name:{},age:{} " , name, age);
    }

    public Student() {
        Console.log("---调用了公有、无参构造方法执行了---");
    }

    private Student(String name) {
        Console.log("有一个参数构造：{}", name);
    }

    //受保护的构造方法
    protected Student(boolean n) {
        Console.log("受保护的构造方法 n={} " , n);
    }

    //私有构造方法
    private Student(Integer age){
        Console.log("私有的构造方法   年龄：{}", age);
    }



    //多参构造
    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
        Console.log("name：{},age：{}", name, age);
    }


    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);
    }

    public static void main(String[] args) {

        Student st1 = new Student("zhangsan1", 1);
        Student st2 = new Student("zhangsan1", 2);
        Student st3 = new Student("zhangsan3", 3);

        List<Student> list = new ArrayList<>();
        list.add(st1);
        list.add(st2);
        list.add(st3);

        Map<String, List<Student>> result = list.stream().collect(Collectors.groupingBy(Student::getName));

        Console.log("结果:{}" , result.toString());

    }
}