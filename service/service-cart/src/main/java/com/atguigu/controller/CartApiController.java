package com.atguigu.controller;


import com.atguigu.cart.CartInfo;
import com.atguigu.response.result.Result;
import com.atguigu.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartApiController {

    @Autowired
    private CartService cartService;


    @RequestMapping("/addCart")
    public void addCart(@RequestBody CartInfo cartInfo){
        cartService.addCart(cartInfo);
    }

    @RequestMapping("cartList")
    Result cartList(HttpServletRequest request){
        String userId = request.getHeader("userId");

        if(StringUtils.isBlank(userId)){
            userId = request.getHeader("userTempId");

        }
        List<CartInfo> cartInfos = new ArrayList<>();
        cartInfos = cartService.cartList(userId);
        return Result.ok(cartInfos);
    }


    @RequestMapping("/getCartByUserId/{userId}")
    List<CartInfo> getCartByUserId(@PathVariable("userId") String userId){

        List<CartInfo> cartInfos = new ArrayList<>();
        cartInfos = cartService.cartList(userId);
        return cartInfos;
    }

}
