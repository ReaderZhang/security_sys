package com.qqz.security_sys.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

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
 * @since 2021-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="摄像头对象", description="")
public class TbCamera implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty("位置")
    private String address;

    private String sort;

    @ApiModelProperty("用户id")
    private String homerId;

    @ApiModelProperty("图标类型")
    private Integer type;

    @ApiModelProperty("图片")
    private String avator;

    @TableLogic
    @ApiModelProperty("逻辑删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreated;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;


}
