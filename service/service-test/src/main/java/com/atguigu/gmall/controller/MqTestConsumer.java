package com.atguigu.gmall.controller;


import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MqTestConsumer {

    @SneakyThrows
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(value = "test.exchange",durable = "true",autoDelete = "true"),
            value = @Queue(value = "test.queue",autoDelete = "true"),
            key = {"test.routingKey"}

    ))
    public void proccess(Channel channel,Message message,String str) throws IOException {

        System.out.println(111111);

        String m = new String(message.getBody());

        // 消息消费的手动确认(为了保证事务)
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }

}
