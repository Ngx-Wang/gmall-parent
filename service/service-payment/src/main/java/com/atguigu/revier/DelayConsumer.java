package com.atguigu.revier;

import com.alibaba.fastjson.JSON;
import com.atguigu.service.PaymentService;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DelayConsumer {
    @Autowired
    PaymentService paymentService;

    @SneakyThrows
    @RabbitListener(queues = "queue.delay.1")
    public void proccess4(Channel channel, Message message, String jsonMap) throws IOException {

        System.out.println("延迟息消费");
        Map<String, Object> map = new HashMap<>();
        Map mapJson = JSON.parseObject(jsonMap, map.getClass());

        String out_trade_no = (String)mapJson.get("out_trade_no");
        Integer count = (Integer)mapJson.get("count");

        if(count>0){
            // 检查结果尚未支付完成，重新发送检查队列
            count --;
            paymentService.querySendMassage(out_trade_no,count);

            // 消息消费的手动确认(为了保证事务)
            // channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }else {
            System.out.println("达到上限，不在检查支付结果");
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);








        // 消息消费的手动确认(为了保证事务)
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }
}
