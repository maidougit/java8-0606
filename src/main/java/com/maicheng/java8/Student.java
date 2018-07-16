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

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
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