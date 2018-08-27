package com.maicheng.java8.concurrent;

import cn.hutool.core.lang.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:Executor
 *
 * FileName: OneExecutor.java
 * @Author: maido
 * @date:    2018/8/24
 * @since v1.8.0
 */
public class OneExecutor {

    public void testNewCachePoolThreadPool() {

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                Console.log("Runable begin {}", System.currentTimeMillis());
                try {
                    Thread.sleep(1000l);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}