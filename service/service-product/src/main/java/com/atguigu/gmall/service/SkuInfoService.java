package com.atguigu.gmall.service;

import com.atguigu.entity.SkuImage;
import com.atguigu.entity.SkuInfo;
import com.atguigu.entity.SpuSaleAttr;
import com.atguigu.list.Goods;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SkuInfoService extends IService<SkuInfo> {
    IPage<SkuInfo> getSpuInfoList(String page, String limit);

    List<SpuSaleAttr> spuSaleAttrList(Long spuId);

    void saveSkuInfo(SkuInfo skuInfo);

    void skuOnSale(Long skuId);

    void cancelSale(Long skuId);

    SkuInfo getSkuInfoById(Long skuId) throws InterruptedException;

    BigDecimal getSkuPrice(Long skuId);

    List<SkuImage> getImage(Long skuId);

    List<Map<String, Object>> getSkuSearAttrValue(Long spuId);

    Goods GetSkuGoods(Long skuId);
}
