package com.atguigu.gmall.controller;


import com.atguigu.gmall.entity.BaseCategory2;
import com.atguigu.gmall.service.BaseCategory2Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import com.atguigu.response.result.Result;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 二级分类表 前端控制器
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class BaseCategory2Controller {
    @Resource
    private BaseCategory2Service category2Service;

    @GetMapping("/getCategory2/{category1Id}")
    public Result GetCategory2(@PathVariable Integer category1Id){
        QueryWrapper<BaseCategory2> wrapper = new QueryWrapper<>();
        wrapper.eq("category1_id",category1Id);
        List<BaseCategory2> category2List = category2Service.list(wrapper);
        return Result.ok(category2List);

    }
}

