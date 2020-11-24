package com.atguigu.service.impl;

import com.atguigu.ProductFeignClient;
import com.atguigu.cart.CartInfo;
import com.atguigu.entity.SkuInfo;
import com.atguigu.gmall.cacheaop.Gmall;
import com.atguigu.mapper.CartMapper;
import com.atguigu.service.CartService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductFeignClient productFeignClient;

    @Override
    public void addCart(CartInfo cartInfo) {
        Long skuId = cartInfo.getSkuId();
        String userId = cartInfo.getUserId();
        SkuInfo skuInfo = productFeignClient.getSkuInfoById(skuId);

        CartInfo cartCache = new CartInfo();
        QueryWrapper<CartInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_id",skuId);
        queryWrapper.eq("user_id",userId);
        //根据skuid和userId确认时修改还是新增
        CartInfo cartInfoExist = cartMapper.selectOne(queryWrapper);
        if(null!= cartInfoExist){
            //修改
            cartInfoExist.setSkuNum((new BigDecimal(cartInfoExist.getSkuNum()).add(new BigDecimal(cartInfo.getSkuNum()))).intValue());
            cartInfoExist.setCartPrice(skuInfo.getPrice().multiply(new BigDecimal(cartInfoExist.getSkuNum())));
            cartCache = cartInfoExist;
            cartMapper.update(cartInfoExist,queryWrapper);


        }else {
            //新增
            cartInfo.setCartPrice(skuInfo.getPrice().multiply(new BigDecimal(cartInfo.getSkuNum())));
            cartInfo.setIsChecked(1);
            cartInfo.setImgUrl(skuInfo.getSkuDefaultImg());
            cartInfo.setSkuName(skuInfo.getSkuName());
            cartInfo.setSkuNum(cartInfo.getSkuNum());
            cartCache= cartInfo;
            cartMapper.insert(cartInfo);
        }

        redisTemplate.opsForHash().put("cartList:"+cartInfo.getUserId()+":cart",cartInfo.getSkuId()+"",cartCache);


    }

    //@Gmall(prefix ="cartList")
    @Override
    public List<CartInfo> cartList(String userId) {

        QueryWrapper<CartInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<CartInfo> cartInfoList = cartMapper.selectList(queryWrapper);
        for (CartInfo cartInfo : cartInfoList) {
            SkuInfo skuInfo = productFeignClient.getSkuInfoById(cartInfo.getSkuId());
            cartInfo.setSkuPrice(skuInfo.getPrice());
        }
        return cartInfoList;
    }
}
