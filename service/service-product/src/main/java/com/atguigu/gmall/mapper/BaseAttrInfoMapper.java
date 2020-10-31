package com.atguigu.gmall.mapper;

import com.atguigu.entity.BaseAttrInfo;
import com.atguigu.response.AttrInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 属性表 Mapper 接口
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-31
 */
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo> {

    List<AttrInfoVo> attrInfoList(Long category3Id);
}
