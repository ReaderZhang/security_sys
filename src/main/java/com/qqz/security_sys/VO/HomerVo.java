package com.qqz.security_sys.VO;/*
@Author qqz
@create 2021-02-22  17:55
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class HomerVo {
    @ApiModelProperty("用户编号")
    private String homerid;
    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String name;

    @ApiModelProperty(value = "用户性别")
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value = "出生日期")
    private Date birth;
    @ApiModelProperty(value = "急救联系人")
    private String firstAid;

    @ApiModelProperty(value = "急救联系电话")
    private String firstPhone;
}
