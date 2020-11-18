package com.atguigu;


import com.atguigu.order.OrderDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "service-order")
public interface OrderFeignClient {

    @RequestMapping("api/order/getTradeNo/{userId}")
    String getTradeNo(@PathVariable("userId") String userId);

    @RequestMapping("api/order/getDetailArrayList/{userId}")
    List<OrderDetail> getDetailArrayList(@PathVariable("userId") String userId);
}
