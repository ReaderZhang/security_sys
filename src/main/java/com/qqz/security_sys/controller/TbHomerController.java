package com.qqz.security_sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqz.security_sys.Common.Utils.OSSUtil;
import com.qqz.security_sys.Common.Utils.md5DecodeUtil;
import com.qqz.security_sys.Common.response.R;
import com.qqz.security_sys.VO.HomberLogin;
import com.qqz.security_sys.VO.HomerVo;
import com.qqz.security_sys.entity.TbHomer;
import com.qqz.security_sys.service.SmsService;
import com.qqz.security_sys.service.TbHomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-10
 */
@CrossOrigin
@RestController
@RequestMapping("/security_sys/tb-homer")
@Api(value = "用户管理接口",tags = {"用户管理"})
public class TbHomerController {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    private TbHomerService homerService;
    @Autowired
    private SmsService smsService;
    @ApiOperation( value = "登录")
    @PostMapping("/login")
    public R login(@RequestBody HomberLogin homberLogin){
        TbHomer homer = homerService.Login ( homberLogin );
        if (homer!=null){
            return R.ok ().data ( "info" , "登陆成功" ).data ( "user" , homer );
        }
        return R.ClientError ().data ( "info","账号或密码错误" );
    }
    @ApiOperation ( "上传头像" )
    @PostMapping("/uploadAvator/{id}")
    public R uploadAvator(@RequestParam("file")MultipartFile file,@PathVariable("id")String id){
        if (file==null){
            return R.ClientError ().data ( "info","上传文件为空" );
        }
        String filename = file.getOriginalFilename ();
        String url = null;
        if (!filename.contains ( ".jpg" )||!filename.contains ( "png" )){
            try {
                url = OSSUtil.upload ( filename,file.getInputStream () );
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        assert url != null;
        redisTemplate.opsForValue ().set ( id,url );
        return R.ok ().data ( "info","上传成功" );
    }
    @ApiOperation ( "完善信息" )
    @PostMapping("/addHomer")
    public R addHomer(@RequestBody HomerVo homerVo){

        TbHomer homer = new TbHomer ();
        String password = md5DecodeUtil.decode (homerVo.getPassword ());
        homer.setPassword ( password );
        homer.setId ( homerVo.getHomerid () );
        homer.setAvator ( redisTemplate.opsForValue ().get ( homerVo.getHomerid () ) );
        homer.setGender ( homerVo.getGender () );
        homer.setName ( homerVo.getName () );
        homer.setFirstAid ( homerVo.getFirstAid () );
        homer.setFirstPhone ( homerVo.getFirstPhone () );
        homer.setBirth ( homerVo.getBirth () );
        homerService.updateById ( homer );
        return R.ok ().data ( "info","完善信息成功" );
    }
    @ApiOperation ( "获取所有用户(仅供测试使用)" )
    @GetMapping("/homers")
    public R homers(){
        QueryWrapper<TbHomer> wrapper = new QueryWrapper<> ();
        return R.ok ().data ( "data",homerService.getHomers ()  );
    }
    @ApiOperation ( "根据用户id获得个人信息" )
    @GetMapping("/homer/{homerid}")
    public R getHomer(@PathVariable("homerid")@ApiParam("用户编号")String homerid){
        return R.ok ().data ( "data",homerService.getById ( homerid ) );
    }
    @ApiOperation ( "通过验证码修改手机号" )
    @PostMapping("/homer/updatephone/{newphone}/{oldphone}/{code}")
    public R updatephone(@PathVariable("newphone")@ApiParam("新手机号")String newphone,@PathVariable("oldphone")@ApiParam("原手机号")String oldphone,
                         @PathVariable("code")@ApiParam("验证码")String code){
        if (smsService.Validator ( code,oldphone )){
            QueryWrapper<TbHomer> wrapper = new QueryWrapper<> ();
            wrapper.eq ( "phone" , oldphone );
            TbHomer homer = homerService.getOne ( wrapper );
            homer.setPhone ( newphone );
            homerService.saveOrUpdate ( homer );
            return R.ok ().data ( "info","修改成功" );
        }
        return R.ClientError ().data ( "info","验证码错误" );
    }
    @ApiOperation ( "通过验证码修改密码" )
    @PostMapping("/home/updatepassword/{phone}/{password}/{code}")
    public R updatepassword(@PathVariable("phone")@ApiParam("手机号")String phone,@PathVariable("password")@ApiParam("密码")String password,
                            @PathVariable("code")@ApiParam("验证码")String code){
        if(smsService.Validator ( code, phone )){
            QueryWrapper<TbHomer> wrapper = new QueryWrapper<> ();
            wrapper.eq ( "phone",phone );
            TbHomer homer = homerService.getOne ( wrapper );
            homer.setPassword ( md5DecodeUtil.decode ( password ) );
            homerService.saveOrUpdate ( homer );
            return R.ok ().data ( "info","修改成功" );
        }
        return R.ClientError ().data ( "info","验证码错误" );
    }

}

