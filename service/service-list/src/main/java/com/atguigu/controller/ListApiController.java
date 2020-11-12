package com.atguigu.controller;

import com.atguigu.response.result.Result;
import com.atguigu.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/list/api")
@CrossOrigin
public class ListApiController {

    @Autowired
    private ListService listService;

    @RequestMapping("createGoods")
    public Result createGoods(){
        listService.createGoods();
        return Result.ok();
    }

    @RequestMapping("/skuOnSale/{skuId}")
    public Result skuOnSale(@PathVariable("skuId") Long skuId){
        listService.skuOnSale(skuId);
        return Result.ok();
    }

    @RequestMapping("/cancelSale/{skuId}")
    public Result cancelSale(@PathVariable("skuId")Long skuId){
        listService.cancelSale(skuId);

        return Result.ok();
    }

    @RequestMapping("/HotSource/{skuId}")
    Result HotSource(@PathVariable("skuId")Long skuId){
        listService.HotSource(skuId);
        return Result.ok();
    }

}
