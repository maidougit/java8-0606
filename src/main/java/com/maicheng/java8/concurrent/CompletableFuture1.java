package com.maicheng.java8.concurrent;

import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * 描述:TODO
 *
 * FileName: CompletableFuture1.java
 * @Author: maido
 * @date:    2018/7/29
 * @since v1.8.0
 */
public class CompletableFuture1 {

    @Test
    public void test() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("100");

        completableFuture.thenAccept(Console::log)
                .thenAccept(v -> Console.log("结果：{}", v));
    }
}