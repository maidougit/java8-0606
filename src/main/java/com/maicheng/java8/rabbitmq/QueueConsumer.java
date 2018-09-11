package com.maicheng.java8.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 描述:消费者端
 *
 * FileName: QueueConsumer.java
 * @Author: maido
 * @date:    2018/9/11
 * @since v1.8.0
 */
public class QueueConsumer extends EndPoint implements Runnable, Consumer {

    public QueueConsumer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");
    }

    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(bytes);
        System.out.println("Message Number "+ map.get("message number") + " received.");
    }

    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {}

    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}
}