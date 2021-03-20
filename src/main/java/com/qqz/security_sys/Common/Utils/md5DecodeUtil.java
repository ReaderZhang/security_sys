package com.qqz.security_sys.Common.Utils;/*
@Author qqz
@create 2021-02-19  2:30
*/

import org.springframework.util.DigestUtils;

public class md5DecodeUtil {
    public static final String salt = "1024qqz";
    public static String decode(String info){
        String undecode = info+"/"+salt;
        String code = DigestUtils.md5DigestAsHex ( undecode.getBytes () );
        return code;
    }
}
