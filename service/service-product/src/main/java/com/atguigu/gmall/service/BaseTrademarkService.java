package com.atguigu.gmall.service;

import com.atguigu.entity.BaseTrademark;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-01
 */
public interface BaseTrademarkService extends IService<BaseTrademark> {

    IPage<BaseTrademark> getBaseTrademark(String page, String pageSize);
}
