package com.atguigu.gmall.controller;


import com.atguigu.entity.BaseAttrValue;
import com.atguigu.gmall.service.BaseAttrInfoService;
import com.atguigu.response.AttrInfoVo;
import com.atguigu.response.result.Result;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    //根据3级id获得该spu的平台属性列表
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable Long category1Id,
                               @PathVariable Long category2Id,
                               @PathVariable Long category3Id){
        List<AttrInfoVo> attrInfoList = attrInfoService.attrInfoList(category1Id, category2Id, category3Id);
        return Result.ok(attrInfoList);
    }
    //保存该spu的平台属性
    @RequestMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody AttrInfoVo attrInfoVo){
        attrInfoService.saveAttrInfo(attrInfoVo);
        return Result.ok(null);

    }
    //根据平台属性id，获得平台属性值列表
    @GetMapping("getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable String attrId){
        List<BaseAttrValue> attrValueList = attrInfoService.getAttrValueList(attrId);
        return Result.ok(attrValueList);
    }

}

