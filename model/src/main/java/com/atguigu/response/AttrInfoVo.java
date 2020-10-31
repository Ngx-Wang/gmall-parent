package com.atguigu.response;

import com.atguigu.entity.BaseAttrValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Data
public class AttrInfoVo {
    /*
    {
            "id":1,
            "attrName":"价格",
            "categoryId":61,
            "categoryLevel":3,
            "attrValueList":[
                {
                    "id":53,
                    "valueName":"1000-1699",
                    "attrId":1
                },
                …
            ]
        },
     */
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "属性名称")
    private String attrName;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "分类层级")
    private Integer categoryLevel;

    @ApiModelProperty(value = "属性值集合")
    private List<BaseAttrValue> attrValueList= new ArrayList<>();


}
