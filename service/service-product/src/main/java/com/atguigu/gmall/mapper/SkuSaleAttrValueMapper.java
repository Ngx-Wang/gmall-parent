package com.atguigu.gmall.mapper;

import com.atguigu.entity.SkuInfo;
import com.atguigu.entity.SkuSaleAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-01
 */
@Mapper
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {

    List<Map<String, Object>> getSkuSearAttrValue(@Param("spuId") Long spuId);
}
