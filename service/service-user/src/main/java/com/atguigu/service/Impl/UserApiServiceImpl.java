package com.atguigu.service.Impl;

import com.atguigu.mapper.UserAddressMapper;
import com.atguigu.mapper.UserInfoMapper;
import com.atguigu.response.result.Result;
import com.atguigu.service.UserApiService;
import com.atguigu.user.UserAddress;
import com.atguigu.user.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserApiServiceImpl implements UserApiService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserAddressMapper userAddressMapper;

    @Override
    public UserInfo verify(String token) {
        UserInfo userInfo =null;
        Integer userId = (Integer) redisTemplate.opsForValue().get("user:" + token);
        if(null!=userId){
            userInfo= userInfoMapper.selectById(userId);
        }
        return userInfo;

    }

    @Override
    public Map<String, Object> login(UserInfo userInfo) {

        // 验证
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name",userInfo.getLoginName());
        queryWrapper.eq("passwd", DigestUtils.md5DigestAsHex(userInfo.getPasswd().getBytes()));
        UserInfo userInfoResult = userInfoMapper.selectOne(queryWrapper);

        Map<String, Object> map = new HashMap<>();
        if(null!=userInfoResult){
            //设置token
            String token = UUID.randomUUID().toString();
            //同步缓存
            redisTemplate.opsForValue().set("user:"+token,userInfoResult.getId());

            // 返回结果
            map = new HashMap<>();
            map.put("name", userInfoResult.getName());
            map.put("nickName", userInfoResult.getNickName());
            map.put("token", token);
        }

        return  map;


    }

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        QueryWrapper<UserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<UserAddress> list = userAddressMapper.selectList(queryWrapper);
        return list;
    }


}
