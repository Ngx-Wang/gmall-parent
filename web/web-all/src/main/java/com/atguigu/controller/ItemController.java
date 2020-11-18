package com.atguigu.controller;

import com.atguigu.CartFeignClient;
import com.atguigu.ItemFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
public class ItemController {

    @Autowired
    ItemFeignClient itemFeignClient;


    @RequestMapping("{skuId}.html")
    public String index(@PathVariable("skuId") Long skuId, Model model){

        // 调用item的接口
        Map<String,Object> map = new HashMap<>();
        long start = System.currentTimeMillis();
        map = itemFeignClient.getItem(skuId);
        long end = System.currentTimeMillis();
        System.out.println("总时间："+(end-start));

        model.addAllAttributes(map);
        return "item/index";
    }

}
