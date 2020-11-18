package com.atguigu.service.Impl;

import com.atguigu.CartFeignClient;
import com.atguigu.ProductFeignClient;
import com.atguigu.cart.CartInfo;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.order.OrderDetail;
import com.atguigu.service.OrderApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderApiServiceImpl implements OrderApiService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    CartFeignClient cartFeignClient;
    @Override
    public String getTradeNo(String userId) {
        return null;
    }

    @Override
    public List<OrderDetail> getDetailArrayList(String userId) {

       List<CartInfo> CartInfoList = cartFeignClient.getCartByUserId(userId);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartInfo cartInfo : CartInfoList) {
            if(cartInfo.getIsChecked()==1) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setSkuId(cartInfo.getSkuId());
                orderDetail.setSkuName(cartInfo.getSkuName());
                orderDetail.setSkuNum(cartInfo.getSkuNum());
                orderDetail.setImgUrl(cartInfo.getImgUrl());
                orderDetail.setOrderPrice(cartInfo.getCartPrice());
                orderDetailList.add(orderDetail);
            }
        }
        return orderDetailList;
    }
}
