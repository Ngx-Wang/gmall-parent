package com.atguigu.gmall.mapper;

import com.atguigu.entity.BaseAttrValue;
import com.atguigu.response.AttrInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 属性值表 Mapper 接口
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-31
 */
public interface BaseAttrValueMapper extends BaseMapper<BaseAttrValue> {

    public List<BaseAttrValue> getAttrValueByAttrId(Integer id);

}
