package com.atguigu.service;

import com.atguigu.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


public  interface UserApiService {


    UserInfo verify(String token);

    Map<String, Object> login(UserInfo userInfo);
}
