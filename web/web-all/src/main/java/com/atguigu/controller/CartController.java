package com.atguigu.controller;

import com.atguigu.CartFeignClient;
import com.atguigu.cart.CartInfo;
import com.atguigu.user.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {

    @Autowired
    CartFeignClient cartFeignClient;

    @RequestMapping("addCart.html")
    public String index(HttpServletRequest request, CartInfo cartInfo) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId)){
           userId = request.getHeader("userTempId");
        }
        cartInfo.setUserId(userId);
        cartFeignClient.addCart(cartInfo);
        return "redirect:http://cart.gmall.com/cart/success.html";
    }

    @RequestMapping({"cart.html","cart/cart.html"})
    public String cartList(){
        return "cart/index";
    }

}
