package com.atguigu.gmall.service.impl;

import com.atguigu.entity.*;
import com.atguigu.gmall.mapper.*;
import com.atguigu.gmall.service.SpuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-01
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements SpuInfoService {

    @Autowired
    private BaseTrademarkMapper trademarkMapper;

    @Autowired
    private BaseSaleAttrMapper saleAttrMapper;

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;



    @Override
    public List<BaseTrademark> getTrademarkList() {

        List<BaseTrademark> trademarkList = trademarkMapper.selectList(null);
        return trademarkList;
    }

    @Override
    public List<BaseSaleAttr> baseSaleAttrList() {
        List<BaseSaleAttr> saleAttrList = saleAttrMapper.selectList(null);
        return saleAttrList;
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        //保存基本信息
        spuInfoMapper.insert(spuInfo);
        Long spuInfoId = spuInfo.getId();
        //保存图片信息
        List<SpuImage> imageList = spuInfo.getSpuImageList();
        for (SpuImage spuImage : imageList) {
            spuImage.setSpuId(spuInfoId);
            spuImageMapper.insert(spuImage);
        }
        //保存销售属性和销售属性值
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            spuSaleAttr.setSpuId(spuInfoId);
            spuSaleAttrMapper.insert(spuSaleAttr);
            List<SpuSaleAttrValue> saleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue spuSaleAttrValue : saleAttrValueList) {
                spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
                spuSaleAttrValue.setBaseSaleAttrId(spuSaleAttr.getBaseSaleAttrId());
                spuSaleAttrValue.setSpuId(spuInfoId);
                spuSaleAttrValueMapper.insert(spuSaleAttrValue);


            }

        }


    }

    @Override
    public List<SpuImage> spuImageList(String spuId) {
        QueryWrapper<SpuImage> spuImageQueryWrapper = new QueryWrapper<>();
        spuImageQueryWrapper.eq("spu_id",spuId);
        List<SpuImage> spuImageList = spuImageMapper.selectList(spuImageQueryWrapper);
        return spuImageList;
    }
}
