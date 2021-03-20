package com.qqz.security_sys.VO;/*
@Author qqz
@create 2021-02-19  14:56
*/

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录信息")
public class HomberLogin {
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("密码")
    private String password;
}
