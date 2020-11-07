package com.atguigu.gmall.service;

import com.atguigu.entity.BaseCategoryView;
import com.atguigu.entity.SkuImage;
import com.atguigu.entity.SkuInfo;

import java.math.BigDecimal;
import java.util.List;

public interface ProductApiService {
    SkuInfo getSkuInfoById(Long skuId);

    BigDecimal getSkuPrice(Long skuId);

    List<SkuImage> getImage(Long skuId);

    List<String> getspuSaleAttrList(Long skuId);

    BaseCategoryView getCategoryView(Long category3Id);
}
