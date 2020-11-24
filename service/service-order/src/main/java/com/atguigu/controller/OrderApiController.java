package com.atguigu.controller;

import com.atguigu.order.OrderDetail;
import com.atguigu.order.OrderInfo;
import com.atguigu.response.result.Result;
import com.atguigu.service.OrderApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/auth/submitOrder")
    Result submitOrder(String tradeNo, @RequestBody OrderInfo orderInfo, HttpServletRequest request){
        String userId = request.getHeader("userId");
        //1.验证是否是第一次提交，
        Boolean result =orderApiService.checkTradeNo(tradeNo,userId);
        // 如果是，提交，
        if(result){
            orderInfo.setUserId(Long.parseLong(userId));
            orderInfo = orderApiService.submitOrder(orderInfo);

        }else {
            // 不是，报错提示
            return Result.fail("重复提交");
        }
        return  Result.ok(orderInfo.getId());
    }

    @RequestMapping("/getOrderInfoById/{orderId}")
    OrderInfo getOrderInfoById(@PathVariable("orderId") Long orderId){
        OrderInfo orderInfo =orderApiService.getOrderInfoById(orderId);
        return orderInfo;
    }





}
