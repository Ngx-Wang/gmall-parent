package com.atguigu.gmall.service;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.entity.BaseCategory1;
import com.atguigu.entity.BaseCategoryView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 一级分类表 服务类
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-30
 */
public interface BaseCategory1Service extends IService<BaseCategory1> {

    BaseCategoryView getCategoryView(Long category3Id);

    List<JSONObject> categoryList();

}
