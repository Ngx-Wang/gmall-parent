package com.atguigu.service;

import com.atguigu.cart.CartInfo;

import java.util.List;

public interface CartService {
    void addCart(CartInfo cartInfo);

    List<CartInfo> cartList(String userId);
}
