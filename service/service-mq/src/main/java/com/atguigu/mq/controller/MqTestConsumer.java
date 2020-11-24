package com.atguigu.mq.controller;


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
            exchange=@Exchange(value = "test.exchange1",durable = "true",autoDelete = "true"),
            value = @Queue(value = "test.queue1",autoDelete = "true"),
            key = {"test1.routingKey"}))
    public void proccess1(Channel channel,Message message,String JsonStr) throws IOException {

        System.out.println("普通消费");




        // 消息消费的手动确认(为了保证事务)
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }


//    @SneakyThrows
//    @RabbitListener(queues = "queue.dead.1")
//    public void proccess2(Channel channel,Message message,String str) throws IOException {
//
//        System.out.println("死信息消费1");
//
//        // 消息消费的手动确认(为了保证事务)
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//
//    }


    @SneakyThrows
    @RabbitListener(queues = "queue.dead.2")
    public void proccess3(Channel channel,Message message,String str) throws IOException {

        System.out.println("死信息消费2");

        // 消息消费的手动确认(为了保证事务)
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }

    @SneakyThrows
    @RabbitListener(queues = "queue.delay.1")
    public void proccess4(Channel channel,Message message,String str) throws IOException {

        System.out.println("延迟息消费");

        // 消息消费的手动确认(为了保证事务)
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }


}
