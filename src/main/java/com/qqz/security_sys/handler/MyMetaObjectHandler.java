package com.qqz.security_sys.handler;/*
@Author qqz
@create ${YEAR}-${MONTH}-${DAY}  ${TIME}
*/

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName ( "gmtCreate",new Date (),metaObject );
        this.setFieldValByName ( "gmtCreated",new Date (),metaObject );
        this.setFieldValByName ( "gmtModified",new Date (),metaObject );
        this.setFieldValByName ( "gmtCreattime",sdf.format ( new Date () ),metaObject );
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName ( "gmtModified",new Date (),metaObject );
    }
}
