package com.qqz.security_sys.Common.Utils;/*
@Author qqz
@create 2021-03-17  20:22
*/

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {
    private final Logger logger = LoggerFactory.getLogger ( WebLogAspect.class );
    @Pointcut("execution(public * com.qqz.security_sys.controller..*.*(..))")
    public void controllerLog(){}
    @Before ( "controllerLog()" )
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes ();
        HttpServletRequest request = attributes.getRequest ();
        logger.info ( "请求开始.." + request.getRequestURL().toString()+"... 请求方式:"+ request.getMethod()+"... 请求方的ip："
                + request.getRemoteAddr() );
        logger.info ( "请求的参数：" + Arrays.toString(joinPoint.getArgs()) );
    }
    @AfterReturning(returning = "resultValue",pointcut = "controllerLog()")
    public void doAfterReturning(Object resultValue){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("请求返回值："+resultValue);
        logger.info("请求结束.." + request.getRequestURL().toString()+"... 请求方式:"+ request.getMethod()+"... 请求方的ip："
                + request.getRemoteAddr());
    }
}
