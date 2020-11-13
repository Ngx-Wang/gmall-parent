package com.atguigu.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.ListFeignClient;
import com.atguigu.ProductFeignClient;
import com.atguigu.list.SearchParam;
import com.atguigu.list.SearchResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class ListController {
    @Autowired
    ListFeignClient listFeignClient;

    @Autowired
    ProductFeignClient productFeignClient;

    @RequestMapping("index.html")
    public String index(Model model){
        List<JSONObject> categoryList = new ArrayList<>();
        categoryList = productFeignClient.categoryList();
        model.addAttribute("list",categoryList);
        return "index/index";
    }

    @RequestMapping("list.html")
    public String list(SearchParam searchParam, Model model){
        SearchResponseVo searchResponseVo = listFeignClient.SearchList(searchParam);

        model.addAttribute("attrsList",searchResponseVo.getAttrsList());
        model.addAttribute("goodsList",searchResponseVo.getGoodsList());
        model.addAttribute("trademarkList",searchResponseVo.getTrademarkList());

        return "list/index";
    }
}
