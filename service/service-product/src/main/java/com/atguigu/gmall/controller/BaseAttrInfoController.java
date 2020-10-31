package com.atguigu.gmall.controller;


import com.atguigu.gmall.service.BaseAttrInfoService;
import com.atguigu.response.AttrInfoVo;
import com.atguigu.response.result.Result;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 属性表 前端控制器
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-31
 */
@RestController
@RequestMapping("admin/product")
@CrossOrigin
public class BaseAttrInfoController {

    @Autowired
    private BaseAttrInfoService attrInfoService;

    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable Long category1Id,
                               @PathVariable Long category2Id,
                               @PathVariable Long category3Id){
        List<AttrInfoVo> attrInfoList = attrInfoService.attrInfoList(category1Id, category2Id, category3Id);
        return Result.ok(attrInfoList);
    }



}

