package com.atguigu.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * <p>
 * SkuSaleAttrValue
 * </p>
 *
 */
@Data
//@ApiModel(description = "Sku销售属性值")
@TableName("sku_sale_attr_value")
public class SkuSaleAttrValue extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	//@ApiModelProperty(value = "库存单元id")
	@TableField("sku_id")
	private Long skuId;

	///@ApiModelProperty(value = "spu_id(冗余)")
	@TableField("spu_id")
	private Long spuId;

	//@ApiModelProperty(value = "销售属性值id")
	@TableField("sale_attr_value_id")
	private Long saleAttrValueId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public Long getSaleAttrValueId() {
		return saleAttrValueId;
	}

	public void setSaleAttrValueId(Long saleAttrValueId) {
		this.saleAttrValueId = saleAttrValueId;
	}
}

