package com.maicheng.java8.rabbitmq;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

/**
 * 描述:生产者
 *
 * FileName: Producer.java
 * @Author: maido
 * @date:    2018/9/11
 * @since v1.8.0
 */
public class Producer extends EndPoint{

    public Producer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}