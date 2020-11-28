package com.atguigu.service;

import com.atguigu.activity.SeckillGoods;

import java.util.List;

public interface SecKillService {
    void putGoods();

    void publishGoods(Long skuId);

    void unpublishGoods(Long skuId);

    List<SeckillGoods> getSecKillGoodsList();

    SeckillGoods getSeckillGoods(Long skuId);
}
