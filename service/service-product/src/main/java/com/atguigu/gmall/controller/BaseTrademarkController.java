package com.atguigu.gmall.controller;


import com.atguigu.entity.BaseTrademark;
import com.atguigu.gmall.service.BaseTrademarkService;
import com.atguigu.response.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-01
 */
@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class BaseTrademarkController {

    @Autowired
    private BaseTrademarkService trademarkService;
    //查询
    @GetMapping("/baseTrademark/{page}/{pageSize}")
    public Result getBaseTrademark(@PathVariable("page") String page,
                                   @PathVariable("pageSize") String pageSize){

        IPage<BaseTrademark> baseTrademark = trademarkService.getBaseTrademark(page, pageSize);
        return Result.ok(baseTrademark);
    }

    //根据id进行查询
    @GetMapping("/baseTrademark/getBaseTrademarkById/{id}")
    public Result getBaseTrademarkById(@PathVariable("id") String id){
        BaseTrademark trademark = trademarkService.getById(id);
        return Result.ok(trademark);
    }

    //修改
    @PutMapping("/baseTrademark/update")
    public Result updateBaseTrademarkById(BaseTrademark baseTrademark){
        trademarkService.updateById(baseTrademark);
        return Result.ok(null);
    }


    //根据id删除
    @DeleteMapping("/baseTrademark/remove/{id}")
    public Result removeById(@PathVariable("id") String id){
        trademarkService.removeById(id);
        return Result.ok(null);
    }

    //新增
    @PostMapping("/baseTrademark/save")
    public Result save(BaseTrademark baseTrademark){
        trademarkService.save(baseTrademark);
        return Result.ok(null);
    }

}

