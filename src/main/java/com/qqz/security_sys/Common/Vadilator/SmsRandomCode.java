package com.qqz.security_sys.Common.Vadilator;/*
@Author qqz
@create 2021-02-10  17:50
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;
@Component
public class SmsRandomCode {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    public String GetSmsCode(String phone){
        if (redisTemplate.opsForValue ().get ( phone )!=null){
            return redisTemplate.opsForValue ().get ( phone );
        }
        String code = "";
        redisTemplate.opsForValue ().set ( phone,code,5*60*1000 );
        for (int i = 0;i<6;i++){
            code+=(int)(Math.random ()*10);
        }
        return code;
    }
    public boolean Validator(String code,String phone){
        return redisTemplate.opsForValue ().get ( phone ).equals ( code );
    }
}
