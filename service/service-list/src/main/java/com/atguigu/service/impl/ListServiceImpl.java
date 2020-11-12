package com.atguigu.service.impl;

import com.atguigu.ProductFeignClient;
import com.atguigu.list.Goods;
import com.atguigu.repository.GoodsRepository;
import com.atguigu.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    ProductFeignClient productFeignClient;
    @Autowired
    ElasticsearchRestTemplate ElasticsearchRestTemplate;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void skuOnSale(Long skuId) {
       Goods goods = productFeignClient.GetSkuGoods(skuId);
        goodsRepository.save(goods);
    }

    @Override
    public void cancelSale(Long skuId) {
        goodsRepository.deleteById(skuId);
    }

    //初始化，建立索引，建立数据结构
    @Override
    public void createGoods(){
        ElasticsearchRestTemplate.createIndex(Goods.class);
        ElasticsearchRestTemplate.putMapping(Goods.class);
    }

    @Override
    public void HotSource(Long skuId) {

        Long hotSource = redisTemplate.opsForValue().increment("sku:" + skuId + "hotSource", 1l);
        if (hotSource%10 == 0){
            Optional<Goods> repositoryById = goodsRepository.findById(skuId);
            Goods goods = repositoryById.get();
            goods.setHotScore(hotSource);
            goodsRepository.save(goods);
        }
    }

}
