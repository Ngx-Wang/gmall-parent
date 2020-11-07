package com.atguigu.controller;

import com.atguigu.entity.SpuSaleAttr;
import com.atguigu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/item/api")
public class ItemApiController {
    @Autowired
    ItemService itemService;

    @RequestMapping("/getItem/{skuId}")
    Map<String, Object> getItem(@PathVariable("skuId")Long skuId){
        Map<String,Object> map = itemService.getItem(skuId);
        return map;

    };


}
