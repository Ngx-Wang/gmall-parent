package com.atguigu.gmall.controller;


import com.atguigu.entity.BaseCategory3;
import com.atguigu.gmall.service.BaseCategory3Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import com.atguigu.response.result.Result;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 三级分类表 前端控制器
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class BaseCategory3Controller {
    @Resource
    private BaseCategory3Service category3Service;

    @GetMapping("/getCategory3/{category2Id}")
    public Result GetCategory3(@PathVariable Integer category2Id){
        QueryWrapper<BaseCategory3> wrapper = new QueryWrapper<>();
        wrapper.eq("category2_id",category2Id);
        List<BaseCategory3> category3List = category3Service.list(wrapper);
        return Result.ok(category3List);

    }
}

