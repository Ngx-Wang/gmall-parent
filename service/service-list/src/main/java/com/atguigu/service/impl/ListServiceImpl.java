package com.atguigu.service.impl;

import com.atguigu.ProductFeignClient;
import com.atguigu.list.Goods;
import com.atguigu.repository.GoodsRepository;
import com.atguigu.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    ProductFeignClient productFeignClient;
    @Autowired
    ElasticsearchRestTemplate ElasticsearchRestTemplate;

    @Autowired
    GoodsRepository goodsRepository;

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

}
