package com.maicheng.java8.rabbitmq;

import java.util.HashMap;

/**
 * 描述:test rabbitmq
 *
 * FileName: Test.java
 * @Author: maido
 * @date:    2018/9/11
 * @since v1.8.0
 */
public class Test {
    public Test() throws Exception{

        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer("queue");

        for (int i = 0; i < 10; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number "+ i +" sent.");
        }
    }

    public static void main(String[] args) throws Exception{
        new Test();
    }
}