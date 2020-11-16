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
 * 商品图片表
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spu_image")
//@ApiModel(value="SpuImage对象", description="商品图片表")
public class SpuImage implements Serializable {

    private static final long serialVersionUID = 1L;

   // @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

   // @ApiModelProperty(value = "商品id")
    private Long spuId;

   // @ApiModelProperty(value = "图片名称")
    private String imgName;

    //@ApiModelProperty(value = "图片路径")
    private String imgUrl;


}
