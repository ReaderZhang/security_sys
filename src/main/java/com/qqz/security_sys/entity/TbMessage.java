package com.qqz.security_sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.qqz.security_sys.VO.MessageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TbMessage对象", description="")
public class TbMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "信息")
    private String content;

    @ApiModelProperty(value = "图标编号")
    private Integer type;

    @ApiModelProperty(value = "房主编号")
    private String homerid;

    @ApiModelProperty(value = "是否已读")
    private Integer isRead;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "发送时间")
    private Date gmtCreated;

}
