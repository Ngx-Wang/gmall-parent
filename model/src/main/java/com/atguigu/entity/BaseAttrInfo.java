package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 属性表
 * </p>
 *
 * @author wangqiao
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("base_attr_info")
//@ApiModel(value="BaseAttrInfo对象", description="属性表")
public class BaseAttrInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    //@ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //@ApiModelProperty(value = "属性名称")
    private String attrName;

    //@ApiModelProperty(value = "分类id")
    private Long categoryId;

    //@ApiModelProperty(value = "分类层级")
    private Integer categoryLevel;



}
