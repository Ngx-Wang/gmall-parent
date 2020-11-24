package com.atguigu.service;

import com.atguigu.order.OrderDetail;
import com.atguigu.order.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderApiService {

    String getTradeNo(String userId);

    List<OrderDetail> getDetailArrayList(String userId);

    Boolean checkTradeNo(String tradeNo, String userId);

    OrderInfo submitOrder(OrderInfo orderInfo);

    OrderInfo getOrderInfoById(Long orderId);

    void update(Map<String, Object> map);
}
