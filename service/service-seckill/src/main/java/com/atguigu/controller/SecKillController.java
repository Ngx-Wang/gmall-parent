package com.atguigu.controller;

import com.atguigu.activity.SeckillGoods;
import com.atguigu.config.CacheHelper;
import com.atguigu.response.result.Result;
import com.atguigu.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activity/seckill")
public class SecKillController {

    @Autowired
    SecKillService secKillService;

    //秒杀商品入库
    @RequestMapping("/putGoods")
    public Result putGoods(){
        secKillService.putGoods();
        return Result.ok("商品入库成功");
    }

    //秒杀商品发布
    @RequestMapping("/publishGoods/{skuId}")
    public Result publishGoods(@PathVariable("skuId")Long skuId){
        secKillService.publishGoods(skuId);
        return Result.ok("商品发布成功");
    }
    //秒杀商品发布
    @RequestMapping("/unpublishGoods/{skuId}")
    public Result unpublishGoods(@PathVariable("skuId")Long skuId){
        secKillService.unpublishGoods(skuId);
        return Result.ok("商品取消发布");
    }

    @RequestMapping("testPublish/{skuId}")
    public Result testPublish(@PathVariable("skuId")Long skuId){
        Object o = CacheHelper.get(skuId+"");
        return Result.ok(o);
    }






    //获得秒杀商品列表
    @RequestMapping("/getSecKillGoodsList")
    List<SeckillGoods> getSecKillGoodsList(){
       List<SeckillGoods> list = secKillService.getSecKillGoodsList();
        return  list;
    }


    @RequestMapping("getSeckillGoods/{skuId}")
    SeckillGoods getSeckillGoods(@PathVariable("skuId") Long skuId){
        SeckillGoods seckillGood = secKillService.getSeckillGoods(skuId);

        return seckillGood;
    }




}
