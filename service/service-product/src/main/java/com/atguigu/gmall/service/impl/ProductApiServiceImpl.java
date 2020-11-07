package com.atguigu.gmall.service.impl;

import com.atguigu.entity.BaseCategoryView;
import com.atguigu.entity.SkuImage;
import com.atguigu.entity.SkuInfo;
import com.atguigu.gmall.mapper.SkuImageMapper;
import com.atguigu.gmall.mapper.SkuInfoMapper;
import com.atguigu.gmall.service.ProductApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductApiServiceImpl implements ProductApiService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuImageMapper skuImageMapper;





    @Override
    public SkuInfo getSkuInfoById(Long skuId) {


        return null;
    }

    @Override
    public BigDecimal getSkuPrice(Long skuId) {
        return null;
    }

    @Override
    public List<SkuImage> getImage(Long skuId) {
        return null;
    }

    @Override
    public List<String> getspuSaleAttrList(Long skuId) {
        return null;
    }

    @Override
    public BaseCategoryView getCategoryView(Long category3Id) {
        return null;
    }
}
