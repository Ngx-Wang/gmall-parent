package com.atguigu.gmall.service.impl;

import com.atguigu.entity.BaseTrademark;
import com.atguigu.gmall.mapper.BaseTrademarkMapper;
import com.atguigu.gmall.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-01
 */
@Service
public class BaseTrademarkServiceImpl extends ServiceImpl<BaseTrademarkMapper, BaseTrademark> implements BaseTrademarkService {

    @Override
    public IPage<BaseTrademark> getBaseTrademark(String page, String pageSize) {
        long page1 = Long.parseLong(page);
        long limit = Long.parseLong(pageSize);
        IPage<BaseTrademark> trademarkIPage = new Page<>(page1,limit);

        IPage<BaseTrademark> baseTrademarkIPage = baseMapper.selectPage(trademarkIPage, null);
        return baseTrademarkIPage;

    }
}
