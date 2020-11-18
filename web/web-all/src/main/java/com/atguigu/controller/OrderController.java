package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    @RequestMapping("trade.html")
    public String trade(){

        String userId = "";

        return "order/trade";
    }
}
