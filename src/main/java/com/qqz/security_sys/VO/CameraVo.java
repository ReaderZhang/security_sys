package com.qqz.security_sys.VO;/*
@Author qqz
@create 2021-02-26  2:20
*/

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("摄像头映射对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraVo {
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("分类信息")
    private String sort;
    @ApiModelProperty("图形")
    private String avator;
    @ApiModelProperty("图标类别")
    private Integer type;
    @ApiModelProperty("所属用户编号")
    private String homerid;
}
