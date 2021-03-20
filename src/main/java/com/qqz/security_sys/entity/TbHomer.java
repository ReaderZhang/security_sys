package com.qqz.security_sys.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2021-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TbHomer对象", description="")
@NoArgsConstructor
@AllArgsConstructor
public class TbHomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "用户手机")
    private String phone;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String name;

    @ApiModelProperty(value = "用户性别")
    private String gender;
    @ApiModelProperty(value = "出生日期")
    private Date birth;
    @ApiModelProperty(value = "头像")
    private String avator;
    @ApiModelProperty(value = "急救联系人")
    private String firstAid;

    @ApiModelProperty(value = "急救联系电话")
    private String firstPhone;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除标识")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

    private String userId;
    public TbHomer(String phone,String password){
        this.phone = phone;
        this.password = password;
    }

}
