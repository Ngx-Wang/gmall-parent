package com.atguigu.gmall.service;

import com.atguigu.entity.BaseAttrInfo;
import com.atguigu.response.AttrInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 属性表 服务类
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-31
 */
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {

    List<AttrInfoVo> attrInfoList(Long category1Id, Long category2Id, Long category3Id);
}
