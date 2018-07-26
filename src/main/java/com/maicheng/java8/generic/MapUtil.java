package com.maicheng.java8.generic;


import cn.hutool.core.lang.Console;
import lombok.Data;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述:泛型化Map 递归调用
 *
 * FileName: MapUtil.java
 * @Author: maido
 * @date:    2018/7/26
 * @since v1.8.0
 */
public class MapUtil<K, V> extends LinkedHashMap<K,V> {
    public MapUtil() {
    }

    public MapUtil(int size) {
        super(size);
    }

    public MapUtil(K key, V value) {
        this.put(key, value);
    }

    public MapUtil<K, V> push(K key, V value) {
        put(key, value);
        return this;
    }

    public MapUtil<K, V> pushAll(Map<? extends K, ? extends V> map) {
        putAll(map);
        return this;
    }

    @Test
    public void test() {
      MapUtil<String, String> mapUtil = new MapUtil<>();

       mapUtil.push("name", "参数化").push("name1", "参数化");

        Console.log("结果：{}", mapUtil);
    }

}