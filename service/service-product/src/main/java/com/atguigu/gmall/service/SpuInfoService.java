package com.atguigu.gmall.service;

import com.atguigu.entity.BaseSaleAttr;
import com.atguigu.entity.BaseTrademark;
import com.atguigu.entity.SpuImage;
import com.atguigu.entity.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-01
 */
public interface SpuInfoService extends IService<SpuInfo> {

    List<BaseTrademark> getTrademarkList();

    List<BaseSaleAttr> baseSaleAttrList();

    void saveSpuInfo(SpuInfo spuInfo);

    List<SpuImage> spuImageList(String spuId);
}
