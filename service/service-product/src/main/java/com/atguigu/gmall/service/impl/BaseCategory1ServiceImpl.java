package com.atguigu.gmall.service.impl;


import com.atguigu.entity.BaseCategory1;
import com.atguigu.entity.BaseCategoryView;
import com.atguigu.gmall.mapper.BaseCategory1Mapper;
import com.atguigu.gmall.mapper.BaseCategory3Mapper;
import com.atguigu.gmall.mapper.BaseCategoryViewMapper;
import com.atguigu.gmall.service.BaseCategory1Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 一级分类表 服务实现类
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-30
 */
@Service
public class BaseCategory1ServiceImpl extends ServiceImpl<BaseCategory1Mapper, BaseCategory1> implements BaseCategory1Service {

    @Autowired
    private BaseCategoryViewMapper categoryViewMapper;

    @Override
    public BaseCategoryView getCategoryView(Long category3Id) {
        QueryWrapper<BaseCategoryView> wrapper = new QueryWrapper<>();
        wrapper.eq("category3_id",category3Id);
        BaseCategoryView categoryView = categoryViewMapper.selectOne(wrapper);
        return categoryView;
    }
}
