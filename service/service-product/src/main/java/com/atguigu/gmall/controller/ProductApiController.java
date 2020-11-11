package com.atguigu.gmall.controller;

import com.atguigu.entity.BaseCategoryView;
import com.atguigu.entity.SkuImage;
import com.atguigu.entity.SkuInfo;
import com.atguigu.entity.SpuSaleAttr;
import com.atguigu.gmall.service.*;
import com.atguigu.list.Goods;
import com.atguigu.response.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product/api")
public class ProductApiController {

    @Autowired
    SkuInfoService skuService;

    @Autowired
    SpuInfoService spuService;

    @Autowired
    BaseCategory1Service category1Service;

//获得sku的基本信息
    @RequestMapping("/getSkuInfoById/{skuId}")
    SkuInfo getSkuInfoById(@PathVariable("skuId") Long skuId){
        long start = System.currentTimeMillis();
        SkuInfo skuInfo = null;
        try {
            skuInfo = skuService.getSkuInfoById(skuId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("总时间："+(end-start));
        return skuInfo;
    }

    //获得sku的价格
    @RequestMapping("/getSkuPrice/{skuId}")
    BigDecimal getSkuPrice(@PathVariable("skuId") Long skuId){
        BigDecimal price = skuService.getSkuPrice(skuId);
        return price;
    }

    //获得sku的图片
    @RequestMapping("/getImage/{skuId}")
    List<SkuImage> getImage(@PathVariable("skuId") Long skuId){
        List<SkuImage> list = skuService.getImage(skuId);

        return list;
    }

    //获得spu的销售属性
    @RequestMapping("/getspuSaleAttrList/{skuId}/{spuId}")
    List<SpuSaleAttr> getspuSaleAttrList(@PathVariable("skuId") Long skuId,@PathVariable("spuId") Long spuId){
        List<SpuSaleAttr> list = spuService.getspuSaleAttrList(skuId,spuId);

        return list;
    }


    @RequestMapping("/getCategoryView/{category3Id}")
    BaseCategoryView getCategoryView(@PathVariable("category3Id")Long category3Id){
        BaseCategoryView categoryView = category1Service.getCategoryView(category3Id);

        return categoryView;
    }

    @RequestMapping("/getSkuSearAttrValue/{spuId}")
    List<Map<String, Object>> getSkuSearAttrValue(@PathVariable("spuId")Long spuId){
        List<Map<String, Object>> list =skuService.getSkuSearAttrValue(spuId);

        return list;
    }


    @RequestMapping("/GetSkuGoods/{skuId}")
    Goods GetSkuGoods(@PathVariable("skuId")Long skuId){
        Goods goods =skuService.GetSkuGoods(skuId);
        return goods;
    };
    
}
