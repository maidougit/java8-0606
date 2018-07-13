package com.maicheng.java8;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: SingleMethod2Java8.java
 * Author:   hyz
 * Date:     2018-06-06 23:33
 * Description: 简单的java8方式
 */
public class SingleMethod2Java8 {

    public static void main(String[] args) {
        //1. 最简单的Lambda表达式可由逗号分隔的参数列表、->符号和语句块组成，例如：
        // e是由编译器推送出来的
        Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println( e ) );

        //2. 显式指定该参数的类型，例如： 把参数类型与参数包括在括号中
        Arrays.asList( "a", "b", "d" ).forEach( ( String e ) -> System.out.println( e ) );

        //3. 更复杂的语句块，则可以使用花括号将该语句块括起来，类似于Java中的函数体
        Arrays.asList( "a", "b", "d" ).forEach( e -> {
            System.out.print( e );
            System.out.print( e );
        } );


        //Lambda表达式可以引用类成员和局部变量（会将这些变量隐式得转换成final的它们会被隐含的转为final，这样效率更高）
        // ，例如下列两个代码块的效果完全相同：
        String separator = ",";
        Arrays.asList( "a", "b", "d" ).forEach(
                ( String e ) -> System.out.println( e + separator ) );

        //Lambda表达式有返回值，返回值的类型也由编译器推理得出。如果Lambda表达式中的语句块只有一行，
        // 则可以不用使用return语句，下列两个代码片段效果相同：

        // 样例1 排序
        List<String> list = Arrays.asList( "b", "a", "d" );
        list.sort( (e1, e2 ) -> e1.compareTo( e2 ) );

        System.out.println("排序结果--->" + list.toString());
        // 样例2

        Arrays.asList( "b", "a", "d","e" ).sort( (e1,  e2 ) -> {
            int result = e1.compareTo( e2 );

            System.out.println("比较结果---->" + String.format("%s-%s-%s", e1, e2, result));
            return result;
        } );
    }
}