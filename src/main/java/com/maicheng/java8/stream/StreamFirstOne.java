package com.maicheng.java8.stream;

import cn.hutool.core.lang.Console;
import com.maicheng.java8.model.Employee;
import com.maicheng.java8.model.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright (C), 2017-2018, maicheng
 * FileName: StreamFirstOne.java
 * Author:   maidou
 * Date:     2018-06-13 18:09
 * Description: stream 是数据渠道，用于操作数据源（集合，数组等）所生成的元素序列
 *  stream 自己不会存储元素
 *  stream 不会改变源对象。会会有带有结果的新的stream 对象、
 *  stream 操作是延迟执行的。意味着他们会等到需要结果的时候才执行
 */
public class StreamFirstOne {

    List<Employee> employees =  Arrays.asList(
            new Employee(12,12.12,"张三",12),
            new Employee(12,13.13,"李四",13),
            new Employee(12,14.14,"王五",14),
            new Employee(12,14.14,"王五",14),
            new Employee(12,14.14,"王五",14),
            new Employee(12,14.14,"王五",14),
            new Employee(12,15.15,"赵六",15),
            new Employee(12,16.16,"天气",16)
    );

    List<Student> list = Arrays.asList( new Student("zhangsan1", 1),
            new Student("zhangsan1", 2),
            new Student("zhangsan3", 3));

    @Test
    public void test1() {
        //通过Collection 系列集合提供的stream() 或 paralleStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //Array 中的静态的方法 stream() 获取无限流
        Stream<String> stream1 = Arrays.stream(new String[]{});

        Stream<Employee> employeeStream = Arrays.stream(new Employee[10]);

        //Stream 中静态方法of()
        Stream<String> a = Stream.of("a", "b", "c");

        //创建无限流
        Stream<Integer> stream2 = Stream.iterate(1, (x) -> x + 2);

        stream2.limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(Console::log);
    }

    //规约计算 reduce ()
    @Test
    public void test2() {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

       Integer sum =  list.stream().reduce(0, (x, y) -> x+y);

        Console.log("计算结果：{}" , sum);

    }

    @Test
    public void test3() {
        Integer sum = list.stream().collect(Collectors.summingInt(Student::getAge));

        System.out.println("求和结果：" + sum);

        Double resulyt = list.stream().collect(Collectors.averagingDouble(Student::getAge));

        Console.log("平均结果：{}" , sum);

        Optional<Student> max =  list.stream().collect(Collectors.maxBy((x1, x2) -> Integer.compare(x1.getAge(),x2.getAge())));

        Console.log("比较结果：{}" , max.get());
    }

    //groupingby 分组
    @Test
    public void test4() {

        Map<Integer, List<Student>> mapResult  = list.stream().collect(Collectors.groupingBy(Student::getAge));
    }

    /**
     * 筛选和切片
     * filter- 接受lambda 从六种排除某些元素 中间操作不执行
     *
     */
    @Test
    public void test5() {
        // 中间操作： 不会执行任何操作
        Stream<Employee> stream = employees.stream()
                .filter(e->{
                    System.out.println("中间操作");
                    return e.getAge()>12;
                });

        // 种植操作， 一次性执行全部内容，即“惰性求值”
        stream.forEach(System.out::println);
    }

    /**
     * 外部迭代
     */
    @Test
    public void test6(){
        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()) System.out.println(it.next());
    }

    /**
     * limit 截断流，使其元素不超过给定的数量
     */
    @Test
    public void test7() {

        employees.stream()
                .filter(e-> e.getSalary() > 12.12)
                .limit(2)
                .forEach(Console::log);

    }

    /**
     * 去重 distinct-- 筛选， 通过流所生成元素的hashcode() 和 equals() 去重复元素
     */
    @Test
    public void test8() {
        employees.stream()
                .filter(e-> e.getSalary() > 12.12)
                .distinct()
                .forEach(Console::log);
    }

    /**
     * 映射
     * map -接受lambda 将元素换成其他形式或提取信息。接受一个函数作为参数，该函数会被应用带每个元素上，并将其映射成一个新的元素
     * flatMap - 接受一个函数作为参数，将流中的每一个值换成另一个流。然后把所有流连接成一个流
     */
    @Test
    public void test9() {
        List<String> list = Arrays.asList("aaa","abc","ddd");

        // 将左右元素转换成大写
        list.stream().map(e-> e.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-----------");

        // 提取名字
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        Stream<Stream<Character>> stringStream = list.stream()
                .map(StreamFirstOne::filterCharacter);

        stringStream.forEach((sm)->{
            sm.forEach(System.out::println);
        });

        Stream<Character> smresult = list.stream()
                .flatMap(StreamFirstOne::filterCharacter);

        smresult.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch: str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    /**
     * 排序
     * sorted()  - 自然排序  Comparable
     * sorted (Comparator com) --> 定制排序(Comparator)
     */
    public void test10() {

        List<String> list = Arrays.asList("ccc","bbb","aaa","dddd");

        list.stream()
                .sorted()
                .forEach(System.out::println);

        employees.stream()
                .sorted((e1,e2) -> {
                   if(e1.getAge().equals(e2.getAge())) {
                       return e1.getName().compareTo(e2.getName());
                   } else {
                       return -e1.getName().compareTo(e2.getName());
                   }
                }).forEach(System.out::println);
    }

    /**
     * 查找与匹配
     * allMath 检查是否匹配所有袁旭
     * anyMatch - 检查是否至少匹配一个元素
     * noneMatch - 检查是否没有匹配所有元素
     * findFirst- 返回第一个元素
     * count返回流中元素的总个数
     * max
     * min
     */
    public void test11() {

        boolean b1 = employees.stream()
                .allMatch(e -> e.getAge().equals("12.12"));

        System.out.println(b1);

        boolean b2 = employees.stream()
                .anyMatch(e -> e.getAge().equals("12.12"));

        System.out.println(b2);
        boolean b3 = employees.stream()
                .noneMatch(e -> e.getAge().equals("12.12"));


        System.out.println(b3);

       Optional<Employee> op =  employees.stream()
                .sorted((e1,e2) -> Integer.compare(e1.getAge(),e2.getAge()))
        .findFirst();

        System.out.println(op.get());

        Optional<Employee> opInfo =  employees.stream()
                .filter((e1) -> e1.getAge().equals("12.12"))
                .findFirst();

        System.out.println(opInfo.get());
    }

    @Test
    public void  test12() {
        Long result = employees.stream()
                .count();

        System.out.println("总数--->" + result);

        Optional<Employee> opresult = employees.stream()
                .max((e1, e2) -> Integer.compare(e1.getAge(),e2.getAge()));

        System.out.println("--->" + opresult.get());

        Optional<Double> opresult1 = employees.stream()
                .map(Employee::getSalary)
                .max(Double::compareTo);
    }
}