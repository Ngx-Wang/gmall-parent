//
//
package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * <p>
 * BaseCategoryView
 * </p>
 *
 */
@Data
//@ApiModel(description = "BaseCategoryView")
@TableName("base_category_view")
public class BaseCategoryView extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	//@ApiModelProperty(value = "一级分类编号")
	@TableField("category1_id")
	private Long category1Id;

	//@ApiModelProperty(value = "一级分类名称")
	@TableField("category1_name")
	private String category1Name;

	//@ApiModelProperty(value = "二级分类编号")
	@TableField("category2_id")
	private Long category2Id;

	//@ApiModelProperty(value = "二级分类名称")
	@TableField("category2_name")
	private String category2Name;

	//@ApiModelProperty(value = "三级分类编号")
	@TableField("category3_id")
	private Long category3Id;

	//@ApiModelProperty(value = "三级分类名称")
	@TableField("category3_name")
	private String category3Name;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getCategory1Id() {
		return category1Id;
	}

	public void setCategory1Id(Long category1Id) {
		this.category1Id = category1Id;
	}

	public String getCategory1Name() {
		return category1Name;
	}

	public void setCategory1Name(String category1Name) {
		this.category1Name = category1Name;
	}

	public Long getCategory2Id() {
		return category2Id;
	}

	public void setCategory2Id(Long category2Id) {
		this.category2Id = category2Id;
	}

	public String getCategory2Name() {
		return category2Name;
	}

	public void setCategory2Name(String category2Name) {
		this.category2Name = category2Name;
	}

	public Long getCategory3Id() {
		return category3Id;
	}

	public void setCategory3Id(Long category3Id) {
		this.category3Id = category3Id;
	}

	public String getCategory3Name() {
		return category3Name;
	}

	public void setCategory3Name(String category3Name) {
		this.category3Name = category3Name;
	}
}

