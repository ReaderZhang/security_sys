package com.qqz.security_sys.Common.response;/*
@Author qqz
@create ${YEAR}-${MONTH}-${DAY}  ${TIME}
*/

import com.qqz.security_sys.Common.ResponseResult;
import com.qqz.security_sys.VO.MessageDivide;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object> ();

    //把构造方法私有
    private R() {}

    //成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResponseResult.SUCCESS);
        r.setMessage(ResponseResult.MSG_SUCCESS);
        return r;
    }

    //默认失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode( ResponseResult.SERVER_ERROR );
        r.setMessage(ResponseResult.MSG_SERVER_ERROR);
        return r;
    }
    //没有权限静态方法
    public static R AuthorizationError() {
        R r = new R();
        r.setSuccess(false);
        r.setCode( ResponseResult.NO_AUTHORIZATION );
        r.setMessage(ResponseResult.MSG_NO_AUTHORIZATION);
        return r;
    }
    //客户端失败静态方法
    public static R ClientError() {
        R r = new R();
        r.setSuccess(false);
        r.setCode( ResponseResult.CLIENT_ERROR );
        r.setMessage(ResponseResult.MSG_CLIENT_ERROR);
        return r;
    }
    //客户端失败静态方法
    public static R VadilatorError() {
        R r = new R();
        r.setSuccess(false);
        r.setCode( ResponseResult.VALIDATE_ERROR );
        r.setMessage(ResponseResult.MSG_VALIDATE_ERROR);
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
