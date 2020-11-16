package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基本销售属性表
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("base_sale_attr")
//@ApiModel(value="BaseSaleAttr对象", description="基本销售属性表")
public class BaseSaleAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    //@ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //@ApiModelProperty(value = "销售属性名称")
    private String name;


}
