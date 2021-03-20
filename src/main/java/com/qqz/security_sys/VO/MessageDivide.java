package com.qqz.security_sys.VO;/*
@Author qqz
@create 2021-02-20  2:46
*/

import com.qqz.security_sys.entity.TbMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分类好的信息")
public class MessageDivide {
    @ApiModelProperty("信息")
    private List<TbMessage> messages;
    @ApiModelProperty("未读数量")
    private Integer count;
}
