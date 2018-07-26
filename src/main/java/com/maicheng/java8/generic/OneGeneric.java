package com.maicheng.java8.generic;

import cn.hutool.core.lang.Console;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: java 泛型
 * 泛型（Generic type 或者 generics）是对简单的理解，就是对类型的参数化
 *
 * FileName: OneGeneric.java
 * @Author: maido
 * @date:    2018/7/26
 * @since v1.8.0
 */
public class OneGeneric {

    /**
     * 方法test的功能描述:TODO
     * 1、 类型安全。 泛型的主要目标是提高 Java 程序的类型安全。
     * 2、 消除强制类型转换。
     * 3、 潜在的性能收益
     * @Author: maido
     * @date:    2018-07-26 09:43
     * @param
     * @return :
     * @since v1.8.0
     */
    @Test
    public void test() {
        Map m = new HashMap();
        m.put("key", "value");
        String s = (String) m.get("key"); // 需要强制类型转换
        Console.log("获取key的value:{}", s);

        // 泛型化
        Map<String,String> m1 = new HashMap();
        m1.put("key1", "value1");
        String s1 =  m1.get("key1");
        Console.log("获取key1的value:{}", s);
    }

    @Data
    class Point<T>{
        private T var ;

        public String toString() {

            return this.var.toString();
        }
    }

    /**
     * 方法test1的功能描述:泛型测试
     * 命名类型参数（一般释义） K : 键 V : 指 E : 异常类  T : 泛型类
     * @Author: maido
     * @date:    2018-07-26 09:48
     * @param
     * @return :
     * @since v1.8.0
     */
    @Test
    public void test1() {
        // java7及其之后支持菱形操作
        Point<String> p = new Point<>() ;
        p.setVar("maicheng") ;
        Console.log("结果：{}", p.getVar().length()); ;
    }

    //没有指定泛型
    @Test
    public void  test2() {
        Point p = new Point<>() ;
        p.setVar("qingcheng");

        Console.log("我没有指定泛型：{}", p.getVar() );
    }

    public static void fun(Point<Object> point) {
        Console.log("我是point对象：{}", point);
    }

    public static void fun1(Point point) {
        Console.log("我是point无泛型对象：{}", point);
    }

    public static void fun2(Point<?> point) {
        Console.log("我是point未知对象：{}", point);
    }



    /**
     * 方法test3的功能描述:
     * 泛型上限： 表示参数化的类型可能是所指定类型，或者是其子类。
     *
     * @Author: maido
     * @date:    2018-07-26 10:01
     * @param
     * @return :
     * @since v1.8.0
     */
    @Test
    public void test3() {
        Point<String> point = new Point<>();
        point.setVar("100");

        //fun(point);  无法将 Point<String> 转换成 Point<Object>
        fun1(point);
        fun2(point);
    }

    public static void fun5(Point<? extends Number> temp){  // 只能接收Number及其Number的子类
        Console.log("我是point泛型继承对象：{}", temp);
    }

    @Test
    public void test4() {
        Point<Integer> point = new Point<>();
        point.setVar(12);
        fun5(point);
        // 运行报错 Error:(117, 24) java: 名称冲突: fun(com.maicheng.java8.generic.OneGeneric.Point<? extends java.lang.Number>)
        // 和fun(com.maicheng.java8.generic.OneGeneric.Point<java.lang.Object>)具有相同疑符
        Point<String> point1 = new Point<>();
        point1.setVar("123456");
        //fun(point1);
    }

    //泛型方法
    class Demo{
        public <T> T fun(T t){            // 可以接收任意类型的数据
            return t ;          // 直接把参数返回
        }
    }

    @Test
    public void test5() {
        Demo d = new Demo() ;   // 实例化Demo对象
        String str = d.fun("倾城之恋") ;  // 传递字符串
        Console.log("结果：{}", str);
    }

    @Data
     class Info<T extends Number>{ // 指定上限，只能是数字类型
        private T var ;     // 此类型由外部决定

        public String toString(){       // 覆写Object类中的toString()方法
            return this.var.toString() ;
        }
    }

    //通过泛型方法，返回泛型类的实例
    public <T extends Number> Info<T> fun6(T param){
        Info<T> temp = new Info<>() ;      // 根据传入的数据类型实例化Info
        temp.setVar(param) ;        // 将传递的内容设置到Info对象的var属性之中
        return temp ;   // 返回实例化对象
    }


    @Test
    public void test6() {
        Info<Integer> result = fun6(30) ;

        Console.log("结果：{}", result);
    }

    // 泛型数组
    @Test
    public void test7() {
        List<?>[] lsa = new List<?>[10]; // ok, array of unbounded wildcard type
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer>li = new ArrayList<>();
        li.add(new Integer(3));
        oa[1] = li; //correct
       // String s = (String) lsa[1].get(0);// run time error, but cast is explicit
        Integer it = (Integer)lsa[1].get(0); // OK
        Arrays.stream(lsa).forEach(x-> Console.log("结果：{}", x));

    }



}