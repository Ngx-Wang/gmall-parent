package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 一级分类表
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("base_category1")
//@ApiModel(value="BaseCategory1对象", description="一级分类表")
public class BaseCategory1 implements Serializable {

    private static final long serialVersionUID = 1L;

    //@ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

   // @ApiModelProperty(value = "分类名称")
    private String name;


}
