package com.atguigu.mq.controller;


import com.atguigu.mq.service.RabbitService;
import com.atguigu.response.result.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqTestController {

    @Autowired
    RabbitService rabbitService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("testSendDelayMq")
    public Result testSendDelayMq(){

        rabbitService.sendDelayMessage("exchange.delay","routing.delay","1", 5l);

        return Result.ok();
    }


    @RequestMapping("testSendDeadMq")
    public Result testSendDeadMq(){

        rabbitService.sendDeadMessage("exchange.dead","routing.dead.1","1", 5l);

        return Result.ok();
    }

    @RequestMapping("testSendMq")
    public Result testSendMq(){

        //rabbitService.sendMessage("test.exchange","test.routingKey","1");
        rabbitTemplate.convertAndSend("test.exchange1","test1.routingKey","111");
        return Result.ok();
    }

}
