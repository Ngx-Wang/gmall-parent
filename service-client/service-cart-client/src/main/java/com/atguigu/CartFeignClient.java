package com.atguigu;

import com.atguigu.cart.CartInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(value = "service-order")
public interface CartFeignClient {

    @RequestMapping("/api/cart/addCart")
    void addCart(@RequestBody CartInfo cartInfo);
}
