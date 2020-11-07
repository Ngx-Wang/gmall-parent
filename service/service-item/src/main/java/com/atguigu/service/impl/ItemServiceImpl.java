package com.atguigu.service.impl;

import com.atguigu.ProductFeignClient;
import com.atguigu.entity.BaseCategoryView;
import com.atguigu.entity.SkuImage;
import com.atguigu.entity.SkuInfo;
import com.atguigu.entity.SpuSaleAttr;
import com.atguigu.response.result.Result;
import com.atguigu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ItemServiceImpl  implements ItemService {

    @Autowired
    ProductFeignClient productFeignClient;


    @Override
    public Map<String, Object> getItem(Long skuId) {

        Map<String, Object> map = new HashMap<>();
//        1 sku信息
        SkuInfo skuInfo = productFeignClient.getSkuInfoById(skuId);
//        2 sku价格
        BigDecimal price = productFeignClient.getSkuPrice(skuId);
//        3 sku图片信息
        List<SkuImage> skuImages = productFeignClient.getImage(skuId);
//        4 spu销售属性列表
        List<SpuSaleAttr> spuSaleAttrList = productFeignClient.getspuSaleAttrList(skuInfo.getId(),skuInfo.getSpuId());
//        5分类导航
        BaseCategoryView baseCategoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
//        6封装map，k值需要看界面的属性
        skuInfo.setSkuImageList(skuImages);
        map.put("categoryView",baseCategoryView);
        map.put("skuInfo",skuInfo);
        map.put("price",price);
        map.put("spuSaleAttrList",spuSaleAttrList);

        return map;

    }
}
