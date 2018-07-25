package com.lx.java8.lambda_lx;

/**
 * FileName: MyLongOperation.java
 * Author:   lx
 * Date:     2018-07-21 17:11
 * Description:
 */
@FunctionalInterface
public interface MyLongOperation<T, R> {
    R getValue(T t1, T t2);
}

