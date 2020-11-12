package com.atguigu.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.ListFeignClient;
import com.atguigu.ProductFeignClient;
import com.atguigu.entity.BaseCategoryView;
import com.atguigu.entity.SkuImage;
import com.atguigu.entity.SkuInfo;
import com.atguigu.entity.SpuSaleAttr;
import com.atguigu.response.result.Result;
import com.atguigu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.function.Supplier;


@Service
public class ItemServiceImpl  implements ItemService {

    @Autowired
    ProductFeignClient productFeignClient;
    @Resource
    ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    ListFeignClient listFeignClient;

/*    @Override
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

        List<Map<String,Object>> skuSearAttrValues =  productFeignClient.getSkuSearAttrValue(skuInfo.getSpuId());
        Map<String, Object> jsonMap = new HashMap<>();
        for (Map<String, Object> skuSearAttrValueMap : skuSearAttrValues) {
            jsonMap.put(skuSearAttrValueMap.get("valueIds")+"",skuSearAttrValueMap.get("sku_id"));
        }
//        6封装map，k值需要看界面的属性
        skuInfo.setSkuImageList(skuImages);
        map.put("categoryView",baseCategoryView);
        map.put("skuInfo",skuInfo);
        map.put("price",price);
        map.put("spuSaleAttrList",spuSaleAttrList);
        map.put("valuesSkuJson",JSON.toJSONString(jsonMap));
        return map;

    }*/
    @Override
    public Map<String, Object> getItem(Long skuId) {
        //记录热点值
        listFeignClient.HotSource(skuId);
        //显示商品详情，skuInfo
        return getItemByThreadPool(skuId);
    }

    private Map<String, Object> getItemByThreadPool(Long skuId) {
        Map<String, Object> map = new HashMap<>();
        long startCurrentTimeMillis = System.currentTimeMillis();

        CompletableFuture<SkuInfo> skuInfoCompletableFuture = CompletableFuture.supplyAsync(new Supplier<SkuInfo>() {
            @Override
            public SkuInfo get() {
                // 1 sku信息
                SkuInfo skuInfo = productFeignClient.getSkuInfoById(skuId);
                List<SkuImage> skuImages = productFeignClient.getImage(skuId);
                // 3 sku图片信息
                skuInfo.setSkuImageList(skuImages);
                map.put("skuInfo",skuInfo);
                return skuInfo;
            }
        }, threadPoolExecutor);
        CompletableFuture<Void> spuSaleAttrList = skuInfoCompletableFuture.thenAcceptAsync(new Consumer<SkuInfo>() {
            @Override
            public void accept(SkuInfo skuInfo) {
                //        4 spu销售属性列表
                List<SpuSaleAttr> spuSaleAttrList = productFeignClient.getspuSaleAttrList(skuInfo.getId(), skuInfo.getSpuId());
                map.put("spuSaleAttrList", spuSaleAttrList);
            }
        }, threadPoolExecutor);

        CompletableFuture<Void> categoryView = skuInfoCompletableFuture.thenAcceptAsync(new Consumer<SkuInfo>() {
            @Override
            public void accept(SkuInfo skuInfo) {
                //        5分类导航
                BaseCategoryView baseCategoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
                map.put("categoryView", baseCategoryView);
            }
        }, threadPoolExecutor);

        CompletableFuture<Void> skuSearAttrValues = skuInfoCompletableFuture.thenAcceptAsync(new Consumer<SkuInfo>() {
            @Override
            public void accept(SkuInfo skuInfo) {
                //        6封装map，k值需要看界面的属性
                List<Map<String, Object>> skuSearAttrValues = productFeignClient.getSkuSearAttrValue(skuInfo.getSpuId());
                Map<String, Object> jsonMap = new HashMap<>();
                for (Map<String, Object> skuSearAttrValueMap : skuSearAttrValues) {
                    jsonMap.put(skuSearAttrValueMap.get("valueIds") + "", skuSearAttrValueMap.get("sku_id"));
                }
                map.put("valuesSkuJson", JSON.toJSONString(jsonMap));
            }
        }, threadPoolExecutor);
        CompletableFuture<Void> price = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                //        2 sku价格
                BigDecimal price = productFeignClient.getSkuPrice(skuId);
                map.put("price", price);
            }
        }, threadPoolExecutor);
        CompletableFuture.allOf(skuInfoCompletableFuture,skuSearAttrValues,categoryView,price,spuSaleAttrList).join();






        long endCurrentTimeMillis = System.currentTimeMillis();
        System.out.println("线程池总耗时："+(endCurrentTimeMillis-startCurrentTimeMillis));

        return map;
    }
}
