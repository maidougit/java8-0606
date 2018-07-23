package com.maicheng.java8.lambda_lx;

/**
 * Copyright (C), 2017-2018, 上海阁为信息科技有限公司
 * FileName: MyLongOperation.java
 * Author:   lx
 * Date:     2018-07-21 17:11
 * Description:
 */
@FunctionalInterface
public interface MyLongOperation<T, R> {
    R getValue(T t1, T t2);
}

