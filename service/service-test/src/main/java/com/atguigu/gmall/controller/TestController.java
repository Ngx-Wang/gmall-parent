package com.atguigu.gmall.controller;

import com.atguigu.gmall.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.response.result.Result;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/service/test/")
public class TestController {

    @Autowired
    MqService mqService;


    @RequestMapping("/test1")
    public Result test1(){
        return Result.ok("111");
    }

    @RequestMapping("/sendMessage")
    public Result sendMessage() throws IOException, TimeoutException {

        mqService.sendMessage("test.exchange","test.routingKey","1");
        return Result.ok();
    }

}
