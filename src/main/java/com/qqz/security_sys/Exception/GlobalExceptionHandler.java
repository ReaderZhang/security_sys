package com.qqz.security_sys.Exception;/*
@Author qqz
@create ${YEAR}-${MONTH}-${DAY}  ${TIME}
*/

import com.qqz.security_sys.Common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace ();
        return R.error ();
    }
}
