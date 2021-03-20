package com.qqz.security_sys.VO;/*
@Author qqz
@create 2021-02-26  2:38
*/

import io.swagger.annotations.ApiModelProperty;

public class CameraUpdateVo {
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("分类信息")
    private String sort;
    @ApiModelProperty("图形")
    private String avator;
    @ApiModelProperty("图标类别")
    private Integer type;
}
