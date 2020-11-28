package com.atguigu;

import com.atguigu.activity.SeckillGoods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@FeignClient(value = "service-activity")
public interface SecKillFeignClient {
    @RequestMapping("/api/activity/seckill/getSecKillGoodsList")
    List<SeckillGoods> getSecKillGoodsList();

    @RequestMapping("/api/activity/seckill/getSeckillGoods/{skuId}")
    SeckillGoods getSeckillGoods(@PathVariable("skuId") Long skuId);

}
