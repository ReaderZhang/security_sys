package com.qqz.security_sys.Common;/*
@Author qqz
@create 2021-02-10  16:10
*/

public interface ResponseResult {
    public static final Integer SUCCESS = 20000;
    public static final Integer NO_AUTHORIZATION=20001;
    public static final Integer SERVER_ERROR=20005;
    public static final Integer CLIENT_ERROR=20006;
    public static final Integer VALIDATE_ERROR=44444;

    public static final String MSG_SUCCESS = "请求成功";
    public static final String MSG_NO_AUTHORIZATION = "没有权限访问";
    public static final String MSG_SERVER_ERROR = "服务器出错";
    public static final String MSG_CLIENT_ERROR = "客户端出错";
    public static final String MSG_VALIDATE_ERROR = "格式出错";
}
