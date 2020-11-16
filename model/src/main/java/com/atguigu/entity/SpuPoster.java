package com.atguigu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品海报表
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spu_poster")
//@ApiModel(value="SpuPoster对象", description="商品海报表")
public class SpuPoster implements Serializable {

    private static final long serialVersionUID = 1L;

    //@ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

   /// @ApiModelProperty(value = "商品id")
    private Long spuId;

   // @ApiModelProperty(value = "文件名称")
    private String imgName;

   // @ApiModelProperty(value = "文件路径")
    private String imgUrl;

   // @ApiModelProperty(value = "创建时间")
    private Date createTime;

   // @ApiModelProperty(value = "更新时间")
    private Date updateTime;

   // @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;


}
