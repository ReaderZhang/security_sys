package com.qqz.security_sys.VO;/*
@Author qqz
@create 2021-02-19  13:25
*/

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户注册信息")
public class HomerRegister {
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("验证码")
    private String code;
    @ApiModelProperty("密码")
    private String password;
}
