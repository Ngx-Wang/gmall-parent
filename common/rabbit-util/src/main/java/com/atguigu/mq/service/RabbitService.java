package com.atguigu.mq.service;

import org.springframework.amqp.core.MessagePostProcessor;

public interface RabbitService {
    void sendMessage(String exchange, String routingKey, Object message);

    void sendDeadMessage(String exchange, String routingKey, Object message, Long time);

    void sendDelayMessage(String exchange, String routingKey, Object message, long time);
}
