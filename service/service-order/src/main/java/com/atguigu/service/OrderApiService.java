package com.atguigu.service;

import com.atguigu.order.OrderDetail;

import java.util.List;

public interface OrderApiService {

    String getTradeNo(String userId);

    List<OrderDetail> getDetailArrayList(String userId);
}
