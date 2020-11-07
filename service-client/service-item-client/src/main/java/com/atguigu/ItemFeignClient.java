package com.atguigu;

import com.atguigu.entity.SpuSaleAttr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "service-item")
public interface ItemFeignClient {

    @RequestMapping("/item/api/getItem/{skuId}")
    Map<String, Object> getItem(@PathVariable("skuId")Long skuId);
}
