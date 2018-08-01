package com.lx.java8.MethodReferencesAndConstructorReferences;

import com.lx.java8.lambda_lx.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * FileName: TestMethodReference.java
 * Author:   lx
 * Date:     2018-07-31 8:21
 * Description: 方法引用
 *
 * 一、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 *         （可以理解为方法引用时Lambda表达式的另外一种表现形式）
 *
 * 主要有三种语法格式：
 *
 * 对象::实例方法名
 *
 * 类::静态方法名
 *
 * 类::实例方法名
 *
 * 注意：1 Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！（方法引用的条件：被引用的方法的入参和返回值类型要与Lambda函数的参数和返回值类型一致）
 *      2 若Lambda参数列表中的第一参数是实例方法的调用者，而第二个参数时，可以使用ClassName::method,即类::实例方法名
 *
 *
 * 二、构造器引用
 * 格式：
 * ClassName::new
 *
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
 *
 * 三、数组引用
 *
 * Type[]::new;
 */
public class TestMethodReference {

    // 对象::实例方法名
    @Test
    public void test1() {

        PrintStream ps = System.out;

        Consumer<String> con = (x) -> ps.println();

        PrintStream ps1 = System.out;
        // 对象::实例方法名
        Consumer<String> con1 = ps1::println;
        // 对象::实例方法名
        Consumer<String> con2 = System.out::println;
        con2.accept("kjkjnfidkeh3");

    }

    // 对象::实例方法名
    @Test
    public void test2() {
        Employee employee = new Employee("周星星", 18, 1666.66);
        Supplier<String> sup = () -> employee.getName();
        String str = sup.get();
        System.out.println(str);

        Supplier<Integer> sup2 = employee::getAge;
        System.out.println(sup2.get());
    }

    // 类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com2 = Integer::compare;
    }

    // 类::实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String, String> bp2 = String::equals;
        String string = new String();

    }

    // 构造器引用
    @Test
    public void test5() {
        Supplier<Employee> sup1 = () -> new Employee();

        // 构造器引用方式 这里调用的是无参构造器
        Supplier<Employee> sup = Employee::new;
    }

    // 构造器引用2
    @Test
    public void test6() {
        Function<Integer, Employee> function =  (x) ->  new Employee(x);

        Function<Integer, Employee> fun2 = Employee::new;
        Employee emp = fun2.apply(18);
        System.out.println(emp);
    }

    // 构造器引用3
    @Test
    public void test7() {
        BiFunction<String, Integer, Employee> biFunction = Employee::new;
    }

    // 数组引用
    @Test
    public void test8() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] strings = fun.apply(10);
        System.out.println(strings.length);

        Function<Integer, String[]> fun2 = String[]::new;
        String[] str2 = fun2.apply(20);
        System.out.println(str2.length);

    }

}
