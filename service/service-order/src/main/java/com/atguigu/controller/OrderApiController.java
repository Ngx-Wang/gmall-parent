package com.atguigu.controller;

import com.atguigu.order.OrderDetail;
import com.atguigu.response.result.Result;
import com.atguigu.service.OrderApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderApiController {

    @Autowired
    OrderApiService orderApiService;

    @RequestMapping("/getTradeNo/{userId}")
    String getTradeNo(@PathVariable("userId") String userId){

        String tradeNo = orderApiService.getTradeNo(userId);
        return tradeNo;
    }

    @RequestMapping("/getDetailArrayList/{userId}")
    List<OrderDetail> getDetailArrayList(@PathVariable("userId") String userId){

        List<OrderDetail> list = orderApiService.getDetailArrayList(userId);
        return  list;
    }






}
