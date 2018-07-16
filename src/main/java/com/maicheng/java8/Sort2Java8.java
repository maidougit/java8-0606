package com.maicheng.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright (C), 2017-2018, 上海阁为信息科技有限公司
 * FileName: Sort2Java8.java
 * Author:   hyz
 * Date:     2018-06-06 23:38
 * Description:
 */
public class Sort2Java8 {

    public static void main(String[] args) {

        Student st1 = new Student("zhangsan1", 1);
        Student st2 = new Student("zhangsan2", 2);
        Student st3 = new Student("zhangsan3", 3);

        List<Student> list = new ArrayList<>();
        list.add(st1);
        list.add(st2);
        list.add(st3);

        // 带有参数排序
        list.sort((e1,e2) -> e2.getAge().compareTo(e1.getAge()));

        System.out.println("---->" + list.toString());

        Optional<Student> optionStu = list.stream().filter(stu -> stu.getName().contains("zhangsan1")).findFirst();
        System.out.println("发现第一个对象---->" + optionStu.get());

        List<Student> resultStu = list.stream().filter(stu -> stu.getName().contains("zhangsan")).collect(Collectors.toList());

        System.out.println("拼接对象:---->" + resultStu);


    }

}