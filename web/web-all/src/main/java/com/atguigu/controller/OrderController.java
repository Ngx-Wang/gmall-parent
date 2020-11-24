package com.atguigu.controller;

import com.atguigu.OrderFeignClient;
import com.atguigu.UserFeignClient;
import com.atguigu.order.OrderDetail;
import com.atguigu.user.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    OrderFeignClient orderFeignClient;



    @RequestMapping("trade.html")
    public String trade(Model model, HttpServletRequest request){
        String userId = request.getHeader("userId");

        List<UserAddress> userAddressList = userFeignClient.getUserAddressList(userId);

        String tradeNo = orderFeignClient.getTradeNo(userId);

        List<OrderDetail> detailArrayList =orderFeignClient.getDetailArrayList(userId);

        BigDecimal totalAmount = new BigDecimal("0");
        for (OrderDetail orderDetail : detailArrayList) {
            totalAmount =totalAmount.add(orderDetail.getOrderPrice());
        }
        model.addAttribute("userAddressList",userAddressList);
        model.addAttribute("tradeNo",tradeNo);
        model.addAttribute("detailArrayList",detailArrayList);
        model.addAttribute("totalAmount",totalAmount);
        return "order/trade";
    }
}
