package com.maicheng.java8.lambda_lx;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、Lambda表达式的基础语法： "->" ,称为箭头操作符或Lambda操作符
 * 箭头操作符讲Lambda表达式拆分成两个部分：
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需执行的功能，即Lambda体
 * Lambda表达式需要函数式接口的支持，函数式接口：接口中只有一个函数的接口
 * Lambda表达式的参数列表则为对应接口函数的参数列表
 * Lambda体则为对应接口实现函数中的方法体
 * <p>
 * <p>
 * 语法格式一：无参数，无返回值（即对应接口方法无参数、无返回值）
 * <p>
 * 语法格式二：有一个参数，无返回值
 * <p>
 * 语法格式三：如果只有一个参数，左侧的()可以省略
 * <p>
 * 语法格式四：有多个参数，有返回值，并且Lambda体中有多条语句
 * <p>
 * 语法格式五：若Lambda体中只有一条语句，return 和 大括号都可以不写
 * <p>
 * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出参数的数据类型，即“类型推断”
 * <p>
 * 二、Lambda表达式需要“函数式接口”的支持
 * 函数式接口：只有一个抽象方法的接口。可以使用注解@FunctionalInterface 修饰，该注解用于检查接口是否为函数式接口
 * <p>
 * <p>
 * <p>
 */
public class TestLambda2 {
    /*
     * 语法格式一：无参数，无返回值（即对应接口方法无参数、无返回值）
     */
    @Test
    public void test1() {
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hehehe");
            }
        };
        run1.run();

        System.out.println("-----------------");

        Runnable run2 = () -> System.out.println("666");
        run2.run();

    }

    /**
     * 语法格式二：有一个参数，无返回值
     */
    @Test
    public void test2() {
        Consumer<Employee> con = (e) -> System.out.println(e.toString());

        con.accept(new Employee("李某", 50, 6666.66));
    }

    /**
     * 语法格式三：如果只有一个参数，左侧的()可以省略
     */
    @Test
    public void test3() {
        Consumer<Employee> con = e -> System.out.println(e.toString());

        con.accept(new Employee("李某", 50, 6666.66));
    }

    /**
     * 语法格式四：有多个参数，有返回值，并且Lambda体中有多条语句
     */
    @Test
    public void test4() {
        Comparator<Employee> con = (x, y) -> {
            System.out.println("函数式接口");
            return x.getAge() - y.getAge();
        };

        Integer result = con.compare(new Employee("李某", 50, 6666.66), new Employee("张某", 10, 6666.66));

        System.out.println(result);
    }

    /**
     * 语法格式五：若Lambda体中只有一条语句，return 和 大括号都可以不写
     */
    @Test
    public void test5() {
        Comparator<Employee> con = (x, y) -> x.getAge() - y.getAge();
        Comparator<Employee> con1 = Comparator.comparing(Employee::getAge);

        Integer result = con.compare(new Employee("李某", 50, 6666.66), new Employee("张某", 10, 6666.66));

        System.out.println(result);
    }

    /**
     * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出参数的数据类型，即“类型推断”
     */
    @Test
    public void test6() {
        Comparator<Employee> con = (Employee x, Employee y) -> {
            System.out.println("函数式接口");
            return x.getAge() - y.getAge();
        };

        Integer result = con.compare(new Employee("李某", 50, 6666.66), new Employee("张某", 10, 6666.66));

        System.out.println(result);
    }

    @Test
    public void test7() {
        System.out.println(operation(100, e -> e * e *e));
        System.out.println(operation(100, e -> e + 3));
    }

    Integer operation(Integer i, MyComparator m) {
        return m.turnValue(i);
    }
}
