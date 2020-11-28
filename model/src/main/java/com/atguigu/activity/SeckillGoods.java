package com.atguigu.activity;

import com.atguigu.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(description = "SeckillGoods")
@TableName("seckill_goods")
public class SeckillGoods extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "spu ID")
	@TableField("spu_id")
	private Long spuId;

	@ApiModelProperty(value = "sku ID")
	@TableField("sku_id")
	private Long skuId;

	@ApiModelProperty(value = "标题")
	@TableField("sku_name")
	private String skuName;

	@ApiModelProperty(value = "商品图片")
	@TableField("sku_default_img")
	private String skuDefaultImg;

	@ApiModelProperty(value = "原价格")
	@TableField("price")
	private BigDecimal price;

	@ApiModelProperty(value = "秒杀价格")
	@TableField("cost_price")
	private BigDecimal costPrice;

	@ApiModelProperty(value = "添加日期")
	@TableField("create_time")
	private Date createTime;

	@ApiModelProperty(value = "审核日期")
	@TableField("check_time")
	private Date checkTime;

	@ApiModelProperty(value = "审核状态")
	@TableField("status")
	private String status;

	@ApiModelProperty(value = "开始时间")
	@TableField("start_time")
	private Date startTime;

	@ApiModelProperty(value = "结束时间")
	@TableField("end_time")
	private Date endTime;

	@ApiModelProperty(value = "秒杀商品数")
	@TableField("num")
	private Integer num;

	@ApiModelProperty(value = "剩余库存数")
	@TableField("stock_count")
	private Integer stockCount;

	@ApiModelProperty(value = "描述")
	@TableField("sku_desc")
	private String skuDesc;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getSkuDefaultImg() {
		return skuDefaultImg;
	}

	public void setSkuDefaultImg(String skuDefaultImg) {
		this.skuDefaultImg = skuDefaultImg;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public String getSkuDesc() {
		return skuDesc;
	}

	public void setSkuDesc(String skuDesc) {
		this.skuDesc = skuDesc;
	}
}

