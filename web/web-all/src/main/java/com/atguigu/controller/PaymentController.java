package com.atguigu.controller;

import com.atguigu.OrderFeignClient;
import com.atguigu.order.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {

    @Autowired
    OrderFeignClient orderFeignClient;

    @RequestMapping("pay.html")
    public String Payment(Long orderId, Model model){

        OrderInfo orderInfo = orderFeignClient.getOrderInfoById(orderId);
        model.addAttribute("orderInfo",orderInfo);
        return "payment/pay";
    }


    @RequestMapping("paySuccess")
    public String paySuccess(){

        return "payment/success";
    }
}
