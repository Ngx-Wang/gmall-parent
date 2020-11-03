package com.atguigu.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * SkuInfo
 * </p>
 *
 */
@Data
@ApiModel(description = "SkuInfo")
@TableName("sku_info")
public class SkuInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "商品id")
	@TableField("spu_id")
	private Long spuId;

	@ApiModelProperty(value = "价格")
	@TableField("price")
	private BigDecimal price;

	@ApiModelProperty(value = "sku名称")
	@TableField("sku_name")
	private String skuName;

	@ApiModelProperty(value = "商品规格描述")
	@TableField("sku_desc")
	private String skuDesc;

	@ApiModelProperty(value = "重量")
	@TableField("weight")
	private String weight;

	@ApiModelProperty(value = "品牌(冗余)")
	@TableField("tm_id")
	private Long tmId;

	@ApiModelProperty(value = "三级分类id（冗余)")
	@TableField("category3_id")
	private Long category3Id;

	@ApiModelProperty(value = "默认显示图片(冗余)")
	@TableField("sku_default_img")
	private String skuDefaultImg;

	// 商品的上架，下架操作
	@ApiModelProperty(value = "是否销售（1：是 0：否）")
	@TableField("is_sale")
	private Integer isSale;

	@TableField(exist = false)
	List<SkuImage> skuImageList;

	@TableField(exist = false)
	List<SkuAttrValue> skuAttrValueList;

	@TableField(exist = false)
	List<SkuSaleAttrValue> skuSaleAttrValueList;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getSkuDesc() {
		return skuDesc;
	}

	public void setSkuDesc(String skuDesc) {
		this.skuDesc = skuDesc;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Long getTmId() {
		return tmId;
	}

	public void setTmId(Long tmId) {
		this.tmId = tmId;
	}

	public Long getCategory3Id() {
		return category3Id;
	}

	public void setCategory3Id(Long category3Id) {
		this.category3Id = category3Id;
	}

	public String getSkuDefaultImg() {
		return skuDefaultImg;
	}

	public void setSkuDefaultImg(String skuDefaultImg) {
		this.skuDefaultImg = skuDefaultImg;
	}

	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	public List<SkuImage> getSkuImageList() {
		return skuImageList;
	}

	public void setSkuImageList(List<SkuImage> skuImageList) {
		this.skuImageList = skuImageList;
	}

	public List<SkuAttrValue> getSkuAttrValueList() {
		return skuAttrValueList;
	}

	public void setSkuAttrValueList(List<SkuAttrValue> skuAttrValueList) {
		this.skuAttrValueList = skuAttrValueList;
	}

	public List<SkuSaleAttrValue> getSkuSaleAttrValueList() {
		return skuSaleAttrValueList;
	}

	public void setSkuSaleAttrValueList(List<SkuSaleAttrValue> skuSaleAttrValueList) {
		this.skuSaleAttrValueList = skuSaleAttrValueList;
	}
}

