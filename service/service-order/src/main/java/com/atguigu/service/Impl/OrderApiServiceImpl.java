package com.atguigu.service.Impl;

import com.atguigu.CartFeignClient;
import com.atguigu.ProductFeignClient;
import com.atguigu.cart.CartInfo;
import com.atguigu.enums.OrderStatus;
import com.atguigu.enums.ProcessStatus;
import com.atguigu.mapper.OrderInfoMapper;
import com.atguigu.mapper.OrderDetailMapper;
import com.atguigu.order.OrderDetail;
import com.atguigu.order.OrderInfo;
import com.atguigu.service.OrderApiService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderApiServiceImpl implements OrderApiService {

    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    CartFeignClient cartFeignClient;

    @Autowired
    RedisTemplate redisTemplate;

    //获得标识号
    @Override
    public String getTradeNo(String userId) {
        String tradeNo = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("userId:"+userId+"tradeNo",tradeNo);
        return tradeNo;
    }


    //订单列表详情
    @Override
    public List<OrderDetail> getDetailArrayList(String userId) {

       List<CartInfo> CartInfoList = cartFeignClient.getCartByUserId(userId);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartInfo cartInfo : CartInfoList) {
            if(cartInfo.getIsChecked().intValue()==1) {
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

    @Override
    public Boolean checkTradeNo(String tradeNo, String userId) {
        boolean b =false;
        String tradeForCaChe = (String) redisTemplate.opsForValue().get("userId:" + userId + "tradeNo");

        if(null!= tradeForCaChe&&tradeForCaChe.equals(tradeNo)){
            b=true;
            redisTemplate.delete("userId:"+userId+"tradeNo");
        }
        return b;
    }

    @Override
    public OrderInfo submitOrder(OrderInfo orderInfo) {

        // 保存订单
        String outTradeNo = "atguigu";
        long currentTimeMillis = System.currentTimeMillis();
        outTradeNo += currentTimeMillis;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date());
        outTradeNo+= format;
        orderInfo.setOutTradeNo(outTradeNo);

        orderInfo.setOrderStatus(OrderStatus.UNPAID.getComment());
        orderInfo.setProcessStatus(ProcessStatus.UNPAID.getComment());
        orderInfo.setTotalAmount(getTotalAmount(orderInfo.getOrderDetailList()));
        orderInfo.setOrderComment("快来买");
        orderInfo.setCreateTime(new Date());
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,1);
        Date time = instance.getTime();
        orderInfo.setExpireTime(time);// 一般时24小时候过期
        orderInfo.setImgUrl(orderInfo.getOrderDetailList().get(0).getImgUrl());

        orderInfoMapper.insert(orderInfo);

        // 保存订单详情
        List<OrderDetail> orderDetailList = orderInfo.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setOrderId(orderInfo.getId());
            orderDetailMapper.insert(orderDetail);
        }

        return orderInfo;

    }

    @Override
    public OrderInfo getOrderInfoById(Long orderId) {

        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);

        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        List<OrderDetail> list = orderDetailMapper.selectList(queryWrapper);
        orderInfo.setOrderDetailList(list);
        return orderInfo;
    }

    @Override
    public void update(Map<String, Object> map) {

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderStatus(OrderStatus.PAID.getComment());
        orderInfo.setProcessStatus(ProcessStatus.PAID.getComment());
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("out_trade_no",(String)map.get("out_trade_no"));


        orderInfoMapper.update(orderInfo,queryWrapper);
    }


    private BigDecimal getTotalAmount(List<OrderDetail> detailArrayList) {
        BigDecimal totalAmout = new BigDecimal("0");
        for (OrderDetail orderDetail : detailArrayList) {
            BigDecimal orderPrice = orderDetail.getOrderPrice();
            totalAmout = totalAmout.add(orderPrice);
        }
        return totalAmout;
    }
}
