package com.qqz.security_sys.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import com.qqz.security_sys.VO.AlarmVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@ApiModel(value="TbAlarm对象", description="")
@NoArgsConstructor
public class TbAlarm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "警告编号")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "警告地址")
    private String address;

    @ApiModelProperty(value = "警告区域")
    private String location;

    @ApiModelProperty("行为")
    private String action;

    @ApiModelProperty(value = "用户编号")
    private String userid;
    @TableLogic
    @ApiModelProperty(value = "逻辑删除标志")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "警告时间")
    private Date gmtCreated;
    public TbAlarm(AlarmVo alarmVo){
        this.address = alarmVo.getAddress ();
        this.location = alarmVo.getLocation ();
        this.userid = alarmVo.getUserid ();
        this.action = alarmVo.getAction ();
    }

}
