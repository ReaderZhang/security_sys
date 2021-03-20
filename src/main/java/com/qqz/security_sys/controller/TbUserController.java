package com.qqz.security_sys.controller;


import com.qqz.security_sys.Common.response.R;
import com.qqz.security_sys.VO.UserVo;
import com.qqz.security_sys.entity.TbHomer;
import com.qqz.security_sys.entity.TbUser;
import com.qqz.security_sys.service.TbHomerService;
import com.qqz.security_sys.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-24
 */
@RestController
@Api(value = "紧急联系人接口",tags = {"紧急联系人"})
@CrossOrigin
@RequestMapping("/security_sys/tb-user")
public class TbUserController {

    @Autowired
    private TbUserService userService;
    @Autowired
    private TbHomerService homerService;
    @ApiOperation ( "添加紧急联系人" )
    @PostMapping("/addUser/{homerid}")
    public R addUser(@RequestBody UserVo userVo,@PathVariable("homerid")String homerid){
        TbUser user = new TbUser ();
        user.setPhone ( userVo.getPhone () );
        user.setName ( userVo.getName () );
        userService.save ( user );
        TbHomer homer = homerService.getById ( homerid );
        if (homer.getUserId ()==null){
            homer.setUserId ( user.getId ()+"" );
        }else{
            String str = homer.getUserId ();
            str+=","+user.getId ();
            homer.setUserId ( str );
        }
        homerService.saveOrUpdate ( homer );
        return R.ok ().data ( "info","添加成功" );
    }
    @ApiOperation ( "根据用户id获得紧急联系人" )
    @PostMapping("/User/{homerid}")
    public R getUser(@PathVariable("homerid")@ApiParam("用户id")String homerid){
        TbHomer homer = homerService.getById ( homerid );
        String str = homer.getUserId ();
        String s[] = str.split ( "," );
        List<TbUser> list = new ArrayList<> ();
        for (String ss:s){
            list.add ( userService.getById ( ss ) );
        }
        return R.ok ().data ( "data",list );
    }
    @ApiOperation ( "根据联系人id删除联系人" )
    @DeleteMapping("/deleteUser/{id}")
    public R deleteUser(@PathVariable("id")String id){
        userService.removeById ( id );
        return R.ok ().data ( "info","删除成功" );
    }
    @ApiOperation ( "根据联系人id修改联系人信息" )
    @PostMapping("/updateUser/{id}")
    public R updateUser(@PathVariable("id")String id,@RequestBody UserVo userVo){
        TbUser user = userService.getById ( id );
        user.setPhone ( userVo.getPhone () );
        user.setName ( userVo.getName () );
        userService.saveOrUpdate (user);
        return R.ok ().data ( "info","修改成功" ).data ( "data",user );
    }
}

