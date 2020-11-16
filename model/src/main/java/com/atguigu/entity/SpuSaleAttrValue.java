package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * spu销售属性值
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spu_sale_attr_value")
//@ApiModel(value="SpuSaleAttrValue对象", description="spu销售属性值")
public class SpuSaleAttrValue implements Serializable {

    private static final long serialVersionUID = 1L;

   // @ApiModelProperty(value = "销售属性值编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

   // @ApiModelProperty(value = "商品id")
    private Long spuId;

    //@ApiModelProperty(value = "销售属性id")
    private Long baseSaleAttrId;

   // @ApiModelProperty(value = "销售属性值名称")
    private String saleAttrValueName;

   // @ApiModelProperty(value = "销售属性名称(冗余)")
    private String saleAttrName;

    // 是否是默认选中状态
//	@TableField("sale_attr_name")
//	String isChecked; 0 未选中，1选中
    @TableField(exist = false)
    String isChecked;
}
