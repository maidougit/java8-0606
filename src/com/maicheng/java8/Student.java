package com.maicheng.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Student {

    private String name;

    private Integer age;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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

        System.out.println("结果---->" + result.toString());

    }
}