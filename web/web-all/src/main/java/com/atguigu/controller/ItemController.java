package com.atguigu.controller;

import com.atguigu.ItemFeignClient;
import com.atguigu.entity.SpuSaleAttr;
import com.atguigu.response.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ItemController {

    @Autowired
    ItemFeignClient itemFeignClient;


    @RequestMapping("{skuId}.html")
    public String index(@PathVariable("skuId") Long skuId, Model model){
        // 调用item的接口
        Map<String,Object> map = new HashMap<>();
        map = itemFeignClient.getItem(skuId);
        model.addAllAttributes(map);
        return "item/index";
    }






}
