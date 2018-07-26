package com.maicheng.java8.inf;

/**
 * 描述:类型转换
 *
 * FileName: Converter.java
 * @Author: maido
 * @date:    2018/7/25
 * @since v1.8.0
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}