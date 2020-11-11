package com.atguigu;

import com.atguigu.entity.BaseCategoryView;
import com.atguigu.entity.SkuImage;
import com.atguigu.entity.SkuInfo;
import com.atguigu.entity.SpuSaleAttr;
import com.atguigu.list.Goods;
import com.atguigu.response.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@FeignClient(value = "service-product")
public interface ProductFeignClient {

    @RequestMapping("/product/api/getSkuInfoById/{skuId}")
    SkuInfo getSkuInfoById(@PathVariable("skuId")Long skuId);


    @RequestMapping("/product/api/getSkuPrice/{skuId}")
    BigDecimal getSkuPrice(@PathVariable("skuId")Long skuId);


    @RequestMapping("/product/api/getImage/{skuId}")
    List<SkuImage> getImage(@PathVariable("skuId")Long skuId);

    @RequestMapping("/product/api/getspuSaleAttrList/{skuId}/{spuId}")
    List<SpuSaleAttr> getspuSaleAttrList(@PathVariable("skuId") Long skuId, @PathVariable("spuId") Long spuId);

    @RequestMapping("/product/api/getCategoryView/{category3Id}")
    BaseCategoryView getCategoryView(@PathVariable("category3Id")Long category3Id);

    @RequestMapping("/product/api/getSkuSearAttrValue/{spuId}")
    List<Map<String, Object>> getSkuSearAttrValue(@PathVariable("spuId")Long spuId);

    @RequestMapping("/product/api/GetSkuGoods/{skuId}")
    Goods GetSkuGoods(@PathVariable("skuId")Long skuId);
}
