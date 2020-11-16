package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * SpuSaleAttr
 * </p>
 *
 */
@Data
//@ApiModel(description = "销售属性")
@TableName("spu_sale_attr")
public class SpuSaleAttr  {
	
	private static final long serialVersionUID = 1L;
	//@ApiModelProperty(value = "id")
	@TableId(type = IdType.AUTO)
	private Long id;
	//@ApiModelProperty(value = "商品id")
	@TableField("spu_id")
	private Long spuId;

	//@ApiModelProperty(value = "销售属性id")
	@TableField("base_sale_attr_id")
	private Long baseSaleAttrId;

	//@ApiModelProperty(value = "销售属性名称(冗余)")
	@TableField("sale_attr_name")
	private String saleAttrName;

	// 销售属性对象集合 业务所需 销售属性：销售属性值  1： n
	@TableField(exist = false)
	List<SpuSaleAttrValue> spuSaleAttrValueList;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public Long getBaseSaleAttrId() {
		return baseSaleAttrId;
	}

	public void setBaseSaleAttrId(Long baseSaleAttrId) {
		this.baseSaleAttrId = baseSaleAttrId;
	}

	public String getSaleAttrName() {
		return saleAttrName;
	}

	public void setSaleAttrName(String saleAttrName) {
		this.saleAttrName = saleAttrName;
	}

	public List<SpuSaleAttrValue> getSpuSaleAttrValueList() {
		return spuSaleAttrValueList;
	}

	public void setSpuSaleAttrValueList(List<SpuSaleAttrValue> spuSaleAttrValueList) {
		this.spuSaleAttrValueList = spuSaleAttrValueList;
	}
}

