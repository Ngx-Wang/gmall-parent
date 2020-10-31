package com.atguigu.gmall.service.impl;

import com.atguigu.entity.BaseAttrInfo;
import com.atguigu.entity.BaseAttrValue;
import com.atguigu.gmall.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.service.BaseAttrInfoService;
import com.atguigu.response.AttrInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 属性表 服务实现类
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-31
 */
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo> implements BaseAttrInfoService {

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;



    @Override
    public List<AttrInfoVo> attrInfoList(Long category1Id, Long category2Id, Long category3Id) {
        List<AttrInfoVo> list = new ArrayList<>();


        QueryWrapper<BaseAttrInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", category3Id);
        List<BaseAttrInfo> baseAttrInfoList = baseMapper.selectList(wrapper);

        for (BaseAttrInfo baseAttrInfo : baseAttrInfoList) {
            AttrInfoVo attrInfoVo = new AttrInfoVo();
            BeanUtils.copyProperties(baseAttrInfo,attrInfoVo);

            QueryWrapper<BaseAttrValue> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("attr_id",baseAttrInfo.getId());
            List<BaseAttrValue> attrValueList = baseAttrValueMapper.selectList(wrapper1);
            attrInfoVo.setAttrValueList(attrValueList);

            list.add(attrInfoVo);
        }

       // List<AttrInfoVo> list =baseMapper.attrInfoList(category3Id);


        return list;



    }
}
