package com.qqz.security_sys.controller;/*
@Author qqz
@create 2021-02-10  17:20
*/

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqz.security_sys.Common.Utils.md5DecodeUtil;
import com.qqz.security_sys.Common.Vadilator.SmsRandomCode;
import com.qqz.security_sys.Common.response.R;
import com.qqz.security_sys.VO.HomberLogin;
import com.qqz.security_sys.VO.HomerRegister;
import com.qqz.security_sys.entity.SmsJson;
import com.qqz.security_sys.entity.TbHomer;
import com.qqz.security_sys.service.SmsService;
import com.qqz.security_sys.service.TbHomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.io.IOException;

@RestController
@RequestMapping("/sms")
@Api(value = "短信校验接口",tags = {"短信"})
@Slf4j
@CrossOrigin
public class SmsController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TbHomerService homerService;
    public final static String url = "http://apis.haoservice.com/sms/sendv2?";
    public final static String TPL_ID = "xxxxxxxxxxxxx";
    public final static String KEY = "xxxxxxxxxxxxxxxx";

    @ApiOperation ( value = "发送短信")
    @PostMapping("/sendMessage/{phone}")
    public R SendMessage(@PathVariable("phone")@ApiParam("手机号码") String phone) {
        String code = smsService.GetSmsCode ( phone );
        OkHttpClient client = new OkHttpClient ();
        Request request = new Request.Builder ()
                .url ( " http://apis.haoservice.com/sms/sendv2?key=" + KEY + "&mobile=" + phone + "&tpl_id=" + TPL_ID + "&content=【微云科技】若非本人操作，请忽略。您的验证码为"+code+",有效时长为5分钟&paybyvas=false"  )
                .get ().build ();
        Call call = client.newCall ( request );
        try {
            call.execute ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return R.ok (  );
    }
    @ApiOperation ( value = "校验短信并注册")
    @PostMapping("/register")
    public R register(@RequestBody HomerRegister homerRegister){
        String code = homerRegister.getCode ();
        String phone = homerRegister.getPhone ();
        TbHomer homer = new TbHomer ();
        homer.setPhone ( phone );
        homer.setPassword ( md5DecodeUtil.decode (homerRegister.getPassword ()) );
        QueryWrapper<TbHomer> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "phone",phone);
        if (homerService.getOne ( wrapper )!=null){
            return R.ClientError ().data ( "info","账号已存在" );
        }
        boolean flag = smsService.Validator ( code,phone );
        if (flag){
            homerService.save ( homer );
            return R.ok ().data ( "info","注册成功" );
        }
        return R.ClientError ().data ( "info","验证码错误" );
    }
    @ApiOperation ( value = "校验短信并登录")
    @PostMapping("/login/{phone}/{code}")
    public R login(@ApiParam("手机号")@PathVariable("phone")String phone,@PathVariable("code")@ApiParam("验证码，默认123456") String code){
        QueryWrapper<TbHomer> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "phone",phone );
        boolean flag = smsService.Validator ( code,phone );
        if (flag){
            return R.ok ().data ( "data",homerService.getOne ( wrapper ) );
        }
        return R.error ();
    }

}
