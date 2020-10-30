package com.atguigu.gmall.controller;


import com.atguigu.gmall.entity.BaseCategory1;
import com.atguigu.gmall.service.BaseCategory1Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.atguigu.response.result.Result;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 一级分类表 前端控制器
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class BaseCategory1Controller {
    @Resource
    private BaseCategory1Service category1Service;

    @GetMapping("/getCategory1")
    public Result GetCategory1(){

        List<BaseCategory1> category1List = category1Service.list(null);
        return Result.ok(category1List);

    }



}

