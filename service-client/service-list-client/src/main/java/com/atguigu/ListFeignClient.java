package com.atguigu;

import com.atguigu.response.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-list")
public interface ListFeignClient {

    @RequestMapping("/list/api/skuOnSale/{skuId}")
    Result skuOnSale(@PathVariable("skuId") Long skuId);

    @RequestMapping("/list/api/cancelSale/{skuId}")
    Result cancelSale(@PathVariable("skuId")Long skuId);

    @RequestMapping("/list/api/HotSource/{skuId}")
    Result HotSource(@PathVariable("skuId")Long skuId);
}
