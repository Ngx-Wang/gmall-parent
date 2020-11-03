package com.atguigu.gmall.service.impl;

import com.atguigu.entity.*;
import com.atguigu.gmall.mapper.*;
import com.atguigu.gmall.service.SkuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public IPage<SkuInfo> getSpuInfoList(String page, String limit) {
        long page1 = Long.parseLong(page);
        long limit1 = Long.parseLong(limit);
        IPage<SkuInfo> infoPage = new Page<>(page1,limit1);


        IPage<SkuInfo> skuInfoIPage = baseMapper.selectPage(infoPage, null);
        return skuInfoIPage;


    }

    @Override
    public List<SpuSaleAttr> spuSaleAttrList(Long spuId) {
        QueryWrapper<SpuSaleAttr> wrapper = new QueryWrapper<>();
        wrapper.eq("spu_id",spuId);
        List<SpuSaleAttr> saleAttrList = spuSaleAttrMapper.selectList(wrapper);
        for (SpuSaleAttr spuSaleAttr : saleAttrList) {
            QueryWrapper<SpuSaleAttrValue> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("spu_id",spuId);
            queryWrapper1.eq("base_sale_attr_id",spuSaleAttr.getBaseSaleAttrId());
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
}
