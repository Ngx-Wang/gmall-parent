package com.atguigu.service;

import com.atguigu.payment.PaymentInfo;

import java.util.Map;

public interface PaymentService {

    public String pay(Long orderId) ;

    void updateInfo(PaymentInfo paymentInfo);

    Map<String, Object> query(String out_trade_no);

    void querySendMassage(String outTradeNo,Integer count);
}
