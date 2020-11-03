package com.atguigu.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * SpuInfo
 * </p>
 *
 */
@Data
@ApiModel(description = "SpuInfo")
@TableName("spu_info")
public class SpuInfo {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "id")
	@TableId(type = IdType.AUTO)
	private Long id;
	@ApiModelProperty(value = "商品名称")
	@TableField("spu_name")
	private String spuName;

	@ApiModelProperty(value = "商品描述(后台简述）")
	@TableField("description")
	private String description;

	@ApiModelProperty(value = "三级分类id")
	@TableField("category3_id")
	private Long category3Id;

	@ApiModelProperty(value = "品牌id")
	@TableField("tm_id")
	private Long tmId;

	// 销售属性集合 业务所需
	@TableField(exist = false)
	private List<SpuSaleAttr> spuSaleAttrList;

	// 商品图片集合直接 业务所需
	@TableField(exist = false)
	private List<SpuImage> spuImageList;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getSpuName() {
		return spuName;
	}

	public void setSpuName(String spuName) {
		this.spuName = spuName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategory3Id() {
		return category3Id;
	}

	public void setCategory3Id(Long category3Id) {
		this.category3Id = category3Id;
	}

	public Long getTmId() {
		return tmId;
	}

	public void setTmId(Long tmId) {
		this.tmId = tmId;
	}

	public List<SpuSaleAttr> getSpuSaleAttrList() {
		return spuSaleAttrList;
	}

	public void setSpuSaleAttrList(List<SpuSaleAttr> spuSaleAttrList) {
		this.spuSaleAttrList = spuSaleAttrList;
	}

	public List<SpuImage> getSpuImageList() {
		return spuImageList;
	}

	public void setSpuImageList(List<SpuImage> spuImageList) {
		this.spuImageList = spuImageList;
	}
}

