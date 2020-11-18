package com.atguigu;


import com.atguigu.user.UserAddress;
import com.atguigu.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "service-user")
public interface UserFeignClient {

    @RequestMapping("api/user/passport/verify/{token}")
    UserInfo verify(@PathVariable("token") String token);

    @RequestMapping("api/user/passport/getUserAddressList/{userId}")
    List<UserAddress> getUserAddressList(@PathVariable("userId")String userId);
}
