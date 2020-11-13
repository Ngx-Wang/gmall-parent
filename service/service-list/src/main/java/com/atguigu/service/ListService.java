package com.atguigu.service;

import com.atguigu.list.SearchParam;
import com.atguigu.list.SearchResponseVo;

public interface ListService {
    void skuOnSale(Long skuId);

    void cancelSale(Long skuId);

    void createGoods();

    void HotSource(Long skuId);

    SearchResponseVo SearchList(SearchParam searchParam);
}
