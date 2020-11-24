package com.atguigu.revier;



import com.alibaba.fastjson.JSON;
import com.atguigu.mapper.OrderInfoMapper;
import com.atguigu.order.OrderInfo;
import com.atguigu.service.OrderApiService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class OrderConsumer {

    @Autowired
    OrderApiService orderApiService;


    @SneakyThrows
    @RabbitListener(bindings = @QueueBinding(
            exchange=@Exchange(value = "order.exchange",durable = "true",autoDelete = "true"),
            value = @Queue(value = "order.queue",autoDelete = "true"),
            key = {"order.routingKey"}))
    public void proccess1(Channel channel, Message message, String JsonStr) throws IOException {
        System.out.println("支付完成更新订单");
        Map<String, Object> map = new HashMap<>();
        map = JSON.parseObject(JsonStr, map.getClass());
        orderApiService.update(map);

        // 消息消费的手动确认(为了保证事务)
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }


}
