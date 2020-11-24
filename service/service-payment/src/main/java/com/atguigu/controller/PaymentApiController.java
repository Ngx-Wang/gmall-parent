package com.atguigu.controller;


import com.atguigu.OrderFeignClient;
import com.atguigu.ProductFeignClient;
import com.atguigu.enums.PaymentStatus;
import com.atguigu.mq.service.RabbitService;
import com.atguigu.order.OrderInfo;
import com.atguigu.payment.PaymentInfo;
import com.atguigu.response.result.Result;
import com.atguigu.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/payment/alipay")
public class PaymentApiController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    RabbitService rabbitService;

    @Autowired
    OrderFeignClient orderFeignClient;

    @RequestMapping("/submit/{orderId}")
    public String pay(@PathVariable("orderId") Long orderId){
        String form = paymentService.pay(orderId);

        OrderInfo orderInfo = orderFeignClient.getOrderInfoById(orderId);
        paymentService.querySendMassage(orderInfo.getOutTradeNo(),5);




        return form;
    }

    @RequestMapping("callback/return")
    public String callback(HttpServletRequest request){
        String out_trade_no = request.getParameter("out_trade_no");
        String trade_no = request.getParameter("trade_no");
        String queryString = request.getQueryString();

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOutTradeNo(out_trade_no);
        paymentInfo.setPaymentStatus(PaymentStatus.PAID.toString());
        paymentInfo.setTradeNo(trade_no);
        paymentInfo.setCallbackContent(queryString);
        paymentInfo.setCallbackTime(new Date());
        paymentService.updateInfo(paymentInfo);

        return "<form action=\"http://payment.gmall.com/paySuccess\">\n" +
                "</form>\n" +
                "<script>\n" +
                "\tdocument.forms[0].submit();\n" +
                "</script>";
    }

    @RequestMapping("alipay/query/{out_trade_no}")
    public Result query(@PathVariable("out_trade_no") String out_trade_no){
        Map<String,Object> map = paymentService.query(out_trade_no);
        return Result.ok(map);
    }
}
