package com.atguigu.controller;

import com.atguigu.SecKillFeignClient;
import com.atguigu.activity.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class secKillController {

    @Autowired
    SecKillFeignClient secKillFeignClient;

    @RequestMapping("seckill/{skuId}.html")
    public String getSeckillGoods(@PathVariable("skuId") Long skuId, Model model) {

        SeckillGoods seckillGood =  secKillFeignClient.getSeckillGoods(skuId);
        model.addAttribute("item",seckillGood);
        return "seckill/item";
    }


    @RequestMapping("seckill.html")
    public String seckillList(Model model) {
        List<SeckillGoods> seckillGoods = secKillFeignClient.getSecKillGoodsList();
        model.addAttribute("list",seckillGoods);
        return "seckill/index";
    }



}
