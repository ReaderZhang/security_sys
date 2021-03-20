package com.qqz.security_sys.VO;/*
@Author qqz
@create 2021-02-27  21:17
*/

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageUpdateVo {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("文本信息")
    private String content;
    @ApiModelProperty("消息类型")
    private Integer type;
    @ApiModelProperty("用户编号")
    private String userid;
    private Integer is_read;
}
