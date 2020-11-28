package com.atguigu.gmall.service.impl;

import com.atguigu.ListFeignClient;
import com.atguigu.gmall.cacheaop.Gmall;
import com.atguigu.entity.*;
import com.atguigu.gmall.mapper.*;
import com.atguigu.gmall.service.SkuInfoService;
import com.atguigu.list.Goods;
import com.atguigu.list.SearchAttr;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Autowired
    private BaseTrademarkMapper baseTrademarkMapper;
    @Autowired
    BaseCategoryViewMapper baseCategoryViewMapper;
    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    ListFeignClient listFeignClient;


    @Override
    public IPage<SkuInfo> getSpuInfoList(String page, String limit) {
        long page1 = Long.parseLong(page);
        long limit1 = Long.parseLong(limit);
        IPage<SkuInfo> infoPage = new Page<>(page1, limit1);
        IPage<SkuInfo> skuInfoIPage = baseMapper.selectPage(infoPage, null);
        return skuInfoIPage;


    }

    @Override
    public List<SpuSaleAttr> spuSaleAttrList(Long spuId) {
        QueryWrapper<SpuSaleAttr> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id", spuId);
        List<SpuSaleAttr> saleAttrList = spuSaleAttrMapper.selectList(wrapper);
        for (SpuSaleAttr spuSaleAttr : saleAttrList) {
            QueryWrapper<SpuSaleAttrValue> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("spu_id", spuId);
            queryWrapper1.eq("base_sale_attr_id", spuSaleAttr.getBaseSaleAttrId());
            List<SpuSaleAttrValue> spuSaleAttrValues = spuSaleAttrValueMapper.selectList(queryWrapper1);
            spuSaleAttr.setSpuSaleAttrValueList(spuSaleAttrValues);
        }

        return saleAttrList;

    }

    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        //保存skuInfo的信息
        baseMapper.insert(skuInfo);
        Long skuInfoId = skuInfo.getId();
        //保存sku_image的信息
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage skuImage : skuImageList) {
            skuImage.setSkuId(skuInfo.getId());
            skuImage.setSpuImgId(skuInfo.getSpuId());
            skuImageMapper.insert(skuImage);
        }
        //保存sku_attr_value的信息
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        for (SkuAttrValue skuAttrValue : skuAttrValueList) {
            skuAttrValue.setSkuId(skuInfo.getId());
            skuAttrValueMapper.insert(skuAttrValue);
        }
        //保存sku_sale_attr_value的信息
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
            skuSaleAttrValue.setSkuId(skuInfo.getId());
            skuSaleAttrValue.setSpuId(skuInfo.getSpuId());
            skuSaleAttrValueMapper.insert(skuSaleAttrValue);

        }

    }

    @Override
    public void skuOnSale(Long skuId) {
        //更改上架状态
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setIsSale(1);
        baseMapper.updateById(skuInfo);
        //同步搜索引擎
        listFeignClient.skuOnSale(skuId);
    }

    @Override
    public void cancelSale(Long skuId) {
        //更改上架状态
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setIsSale(0);
        baseMapper.updateById(skuInfo);
        //同步搜索引擎
        listFeignClient.cancelSale(skuId);
    }

    @Gmall
    @Override
    public SkuInfo getSkuInfoById(Long skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo = baseMapper.selectById(skuId);
        return skuInfo;
    }

    @Override
    public BigDecimal getSkuPrice(Long skuId) {
        SkuInfo skuInfo = baseMapper.selectById(skuId);
        return skuInfo.getPrice();

    }

    @Gmall(prefix = "images")
    @Override
    public List<SkuImage> getImage(Long skuId) {
        QueryWrapper<SkuImage> wrapper = new QueryWrapper<>();
        wrapper.eq("sku_id", skuId);
        List<SkuImage> list = skuImageMapper.selectList(wrapper);
        return list;

    }

    @Gmall(prefix = "SkuSearAttrValue")
    @Override
    public List<Map<String, Object>> getSkuSearAttrValue(Long spuId) {
        List<Map<String, Object>> list = skuSaleAttrValueMapper.getSkuSearAttrValue(spuId);
        return list;
    }

    @Override
    public Goods GetSkuGoods(Long skuId) {
        Goods goods = new Goods();
        SkuInfo skuInfo = baseMapper.selectById(skuId);
        BaseTrademark baseTrademark = baseTrademarkMapper.selectById(skuInfo.getTmId());
        QueryWrapper<BaseCategoryView> baseCategoryViewWrapper = new QueryWrapper<>();
        baseCategoryViewWrapper.eq("category3_id", skuInfo.getCategory3Id());
        BaseCategoryView baseCategoryView = baseCategoryViewMapper.selectOne(baseCategoryViewWrapper);
        goods.setTitle(skuInfo.getSkuName());
        goods.setId(skuId);
        goods.setTmName(baseTrademark.getTmName());
        goods.setTmLogoUrl(baseTrademark.getLogoUrl());
        goods.setTmId(skuInfo.getTmId());
        goods.setPrice(skuInfo.getPrice().doubleValue());
        goods.setDefaultImg(skuInfo.getSkuDefaultImg());
        goods.setCreateTime(new Date());
        goods.setCategory3Name(baseCategoryView.getCategory3Name());
        goods.setCategory3Id(baseCategoryView.getCategory3Id());
        goods.setCategory2Name(baseCategoryView.getCategory2Name());
        goods.setCategory2Id(baseCategoryView.getCategory2Id());
        goods.setCategory1Name(baseCategoryView.getCategory1Name());
        goods.setCategory1Id(baseCategoryView.getCategory1Id());

        List<SearchAttr> searchAttrs = new ArrayList<>();
        searchAttrs = baseAttrValueMapper.selectBaseAttrInfoListBySkuId(skuId);
        goods.setAttrs(searchAttrs);

        return goods;
    }
}
