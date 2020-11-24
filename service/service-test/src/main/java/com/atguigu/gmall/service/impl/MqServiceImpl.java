package com.atguigu.gmall.service.impl;

import com.atguigu.gmall.service.MqService;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
@Service
public class MqServiceImpl implements MqService {


    @Autowired
    Connection connection;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String exchange, String routingKey, String message) throws IOException, TimeoutException {
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }
}
