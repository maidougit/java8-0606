package com.maicheng.java8.util;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 描述:CatchUtil
 *
 * FileName: CatchUtil.java
 * @Author: maido
 * @date:    2018/7/26
 * @since v1.8.0
 */
public class CatchUtil {

    public static <T,R> R tryDo(T t, Function<T,R> func) {
        try {
            return func.apply(t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public static <T> void tryDo(T t, Consumer<T> func) {
        try {
            func.accept(t);
        } catch (Exception e) {
            e.printStackTrace();  // for log
            throw new RuntimeException(e.getCause());
        }
    }

}