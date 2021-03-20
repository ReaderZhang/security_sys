package com.qqz.security_sys.service.impl;/*
@Author qqz
@create 2021-02-10  19:07
*/

import com.qqz.security_sys.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public String GetSmsCode(String phone) {
        if (redisTemplate.opsForValue ().get ( phone )!=null&&!redisTemplate.opsForValue ().get ( phone ).equals ( "nil" )){
            return redisTemplate.opsForValue ().get ( phone );
        }
        String code = "";
        for (int i = 0;i<6;i++){
            code+=(int)(Math.random ()*10);
        }
        redisTemplate.opsForValue ().set ( phone,code,60*5, TimeUnit.SECONDS );
        log.debug ( "插入到redis中的验证码为:{}",code );
        return code;
    }

    @Override
    public boolean Validator(String code , String phone) {
        String correct = redisTemplate.opsForValue ().get ( phone );
        log.debug ( "校验前redis中的验证码为:{}",correct );
        if (correct==null){
            return false;
        }
        return correct.equals ( code );
    }
}
