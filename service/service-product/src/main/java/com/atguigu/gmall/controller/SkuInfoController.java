package com.atguigu.gmall.controller;


import com.atguigu.entity.SkuInfo;
import com.atguigu.entity.SpuSaleAttr;
import com.atguigu.gmall.service.SkuInfoService;
import com.atguigu.response.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class SkuInfoController {

    @Autowired
    private SkuInfoService skuService;
    @GetMapping("list/{page}/{limit}")
    public Result getSpuInfoList(@PathVariable("page") String page,
                                 @PathVariable("limit") String limit){
        IPage<SkuInfo> spuInfoList = skuService.getSpuInfoList(page, limit);
        return Result.ok(spuInfoList);
    }
    @GetMapping("spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable("spuId") Long spuId){
        List<SpuSaleAttr>spuSaleAttrList = skuService.spuSaleAttrList(spuId);
        return Result.ok(spuSaleAttrList);
    }

    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){
        skuService.saveSkuInfo(skuInfo);
        return Result.ok();
    }

}
