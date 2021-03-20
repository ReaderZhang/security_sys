package com.qqz.security_sys.service;/*
@Author qqz
@create 2021-02-10  19:06
*/

import org.springframework.stereotype.Service;

@Service
public interface SmsService {
    public String GetSmsCode(String phone);

    public boolean Validator(String code,String phone);
}
