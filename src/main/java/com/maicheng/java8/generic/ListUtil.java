package com.maicheng.java8.generic;

import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 描述: list 递归调用
 *
 * FileName: ListUtil.java
 * @Author: maido
 * @date:    2018/7/26
 * @since v1.8.0
 */
public class ListUtil<T> extends LinkedList<T>{

    public ListUtil<T> append(T t) {
        add(t);
        return this;
    }

    public ListUtil<T> append(List<T> t) {
        addAll(t);
        return this;
    }

    @Test
    public void test() {
        ListUtil<String> list = new ListUtil<>();
        list.append("1").append("2");

        Console.log("结果：{}", list.toString());
    }
}