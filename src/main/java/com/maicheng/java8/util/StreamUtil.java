package com.maicheng.java8.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 描述:StreamUtil
 *
 * FileName: StreamUtil.java
 * @Author: maido
 * @date:    2018/7/26
 * @since v1.8.0
 */
public class StreamUtil {

    public static <T,R> List<R> map(List<T> data, Function<T, R> mapFunc) {

        return data.stream().map(mapFunc).collect(Collectors.toList());
    }
}