package com.qqz.security_sys.VO;/*
@Author qqz
@create 2021-02-20  16:00
*/

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("警报映射类")
public class AlarmVo {
    @ApiModelProperty("警报地址")
    private String address;
    @ApiModelProperty("警报区域")
    private String location;
    @ApiModelProperty("行为")
    private String action;
    @ApiModelProperty("警报用户编号")
    private String userid;
}
