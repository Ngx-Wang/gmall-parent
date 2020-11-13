package com.atguigu.gmall.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall.cacheaop.Gmall;
import com.atguigu.entity.BaseCategory1;
import com.atguigu.entity.BaseCategoryView;
import com.atguigu.gmall.mapper.BaseCategory1Mapper;
import com.atguigu.gmall.mapper.BaseCategoryViewMapper;
import com.atguigu.gmall.service.BaseCategory1Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Gmall(prefix = "CategoryView")
    @Override
    public BaseCategoryView getCategoryView(Long category3Id) {
        QueryWrapper<BaseCategoryView> wrapper = new QueryWrapper<>();
        wrapper.eq("category3_id",category3Id);
        BaseCategoryView categoryView = categoryViewMapper.selectOne(wrapper);
        return categoryView;
    }



    @Override
    public List<JSONObject> categoryList() {

        List<BaseCategoryView> categoryViews = categoryViewMapper.selectList(null);
        //返回数据的容器
        List<JSONObject> list1 = new ArrayList<>();
        //分解查询的数据
        Map<Long, List<BaseCategoryView>> CategoryView1 = categoryViews.stream().collect(Collectors.groupingBy(BaseCategoryView::getCategory1Id));

        for (Map.Entry<Long, List<BaseCategoryView>> group1 : CategoryView1.entrySet()) {

            Long group1Key = group1.getKey();
            String category1Name = group1.getValue().get(0).getCategory1Name();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("categoryId",group1Key);
            jsonObject.put("categoryName",category1Name);


            List<JSONObject> list2 = new ArrayList<>();
            Map<Long, List<BaseCategoryView>> CategoryView2 = group1.getValue().stream().collect(Collectors.groupingBy(BaseCategoryView::getCategory2Id));
            for (Map.Entry<Long, List<BaseCategoryView>> group2 : CategoryView2.entrySet()) {

                Long group2Key = group2.getKey();
                String category2Name = group2.getValue().get(0).getCategory2Name();
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("categoryId",group2Key);
                 jsonObject2.put("categoryName",category2Name);

                List<JSONObject> list3 = new ArrayList<>();
                Map<Long, List<BaseCategoryView>> CategoryView3 = group2.getValue().stream().collect(Collectors.groupingBy(BaseCategoryView::getCategory3Id));
                for (Map.Entry<Long, List<BaseCategoryView>> group3 : CategoryView3.entrySet()) {
                    Long group3Key = group3.getKey();
                    String category3Name = group3.getValue().get(0).getCategory3Name();
                    JSONObject jsonObject3 =  new JSONObject();
                    jsonObject3.put("categoryId",group3Key);
                    jsonObject3.put("categoryName",category3Name);
                    list3.add(jsonObject3);

                }
                jsonObject2.put("categoryChild",list3);
                list2.add(jsonObject2);
            }
            jsonObject.put("categoryChild",list2);
            list1.add(jsonObject);

        }



        return list1;
    }
}
