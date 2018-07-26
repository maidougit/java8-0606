package com.maicheng.java8.reflect;

import cn.hutool.core.lang.Console;
import com.maicheng.java8.model.Student;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * 方法的功能描述:反射
 *
 * @Author: maido
 * @date:    2018-07-08 10:01-10:01
 * @since v1.8.0
 */
public class OneReflect {

  /**
   * 方法getObj的功能描述:获取class的几种方式
   * 在运行期间，一个类，只有一个Class对象产生
   * 说明：1. 第一种对象都有了还要反射干什么
   *  2. 需要导入类的包，依赖太强，不导包就抛编译错误
   *  3. 一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法。
   *
   * @Author: maido
   * @date:    2018-07-24 07:16
   * @param
   * @return : void
   * @since v1.8.0
   */
    @Test
    public void getObj() {
        Student student = new Student();
        Class stuClass1 = student.getClass();
        Console.log("第一种方式获取class名称：{}", stuClass1.getName());

        Class stuClass2 = Student.class;
        Console.log("第二种方式获取class名称：{}", stuClass2.getName());

        try {
            Class stuClass3 = Class.forName("com.maicheng.java8.model.Student");
            Console.log("第三种方式获取class名称：{}", stuClass3.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法getAllConstructor的功能描述:获取所有构造
     *
     * @Author: maido
     * @date:    2018-07-24 07:34
     * @param
     * @return : void
     * @since v1.8.0
     */
    @Test
    public void getAllConstructor() throws Exception{
        Class stuClass = Class.forName("com.maicheng.java8.model.Student");
        //所有共有构造
        Arrays.asList(stuClass.getConstructors()).stream().forEach(x->Console.log(x));


    }

    /**
     * 方法getAllConstructorContainsProtect的功能描述:所有的构造方法(包括：私有、受保护、默认、公有)
     *
     * @Author: maido
     * @date:    2018-07-24 07:42
     * @param
     * @return : void
     * @since v1.8.0
     */
    @Test
    public void getAllConstructorContainsProtect() throws Exception{
        Class stuClass = Class.forName("com.maicheng.java8.model.Student");

        Arrays.asList(stuClass.getDeclaredConstructors()).stream().forEach(x->Console.log(x));
    }

    /**
     * 方法getNotParamConstructor的功能描述:获取无参构造
     *
     * @Author: maido
     * @date:    2018-07-24 07:45
     * @param
     * @return : void
     * @since v1.8.0
     */
    @Test
    public void getNotParamConstructor() throws Exception{
        Class clazz = Class.forName("com.maicheng.java8.model.Student");
        Constructor con = clazz.getConstructor(null);

        Console.log("con:{}",con);

        Object obj = con.newInstance();
        Console.log("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(String.class);
        Console.log(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance("男");
    }
}