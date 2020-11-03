package com.atguigu.gmall.service;

import com.atguigu.entity.SkuInfo;
import com.atguigu.entity.SpuSaleAttr;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SkuInfoService extends IService<SkuInfo> {
    IPage<SkuInfo> getSpuInfoList(String page, String limit);

    List<SpuSaleAttr> spuSaleAttrList(Long spuId);

    void saveSkuInfo(SkuInfo skuInfo);
}
