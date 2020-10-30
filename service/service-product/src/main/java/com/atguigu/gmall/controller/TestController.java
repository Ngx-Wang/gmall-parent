package com.atguigu.gmall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.response.result.Result;

@RestController
@RequestMapping("/service/test/")
public class TestController {

    @RequestMapping("/test1")
    public Result test1(){
        return Result.ok("111");
    }


}
