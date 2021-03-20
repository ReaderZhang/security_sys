package com.qqz.security_sys.entity;/*
@Author qqz
@create 2021-02-10  18:43
*/

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmsJson {
    private Integer error_code;
    private String reason;
    private String result;
}
