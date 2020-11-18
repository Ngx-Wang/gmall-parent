package com.atguigu.controller;

import com.atguigu.response.result.Result;
import com.atguigu.service.UserApiService;
import com.atguigu.user.UserAddress;
import com.atguigu.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserApiController {

    @Autowired
    private UserApiService userApiService;

    @RequestMapping("/passport/verify/{token}")
    UserInfo verify(@PathVariable("token") String token){
        UserInfo userInfo = userApiService.verify(token);
        return userInfo;
    }



    @RequestMapping("/passport/login")
    public Result index(@RequestBody UserInfo userInfo){
        Map<String,Object> map = userApiService.login(userInfo);

        if(null!=map){
            return Result.ok(map);
        }else {
            return Result.fail();
        }
    }

    @RequestMapping("/passport/getUserAddressList/{userId}")
    List<UserAddress> getUserAddressList(@PathVariable("userId")String userId){

        List<UserAddress> list = userApiService.getUserAddressList(userId);

        return  list;
    }



}
