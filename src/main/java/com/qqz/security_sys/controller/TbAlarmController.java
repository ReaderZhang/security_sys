package com.qqz.security_sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqz.security_sys.Common.response.R;
import com.qqz.security_sys.VO.AlarmVo;
import com.qqz.security_sys.entity.TbAlarm;
import com.qqz.security_sys.service.TbAlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
@RestController
@CrossOrigin
@Api(value = "警报接口",tags = {"警报"})
@RequestMapping("/security_sys/tb-alarm")
public class TbAlarmController {
    @Autowired
    private TbAlarmService alarmService;

    @ApiOperation ( "移除警报" )
    @DeleteMapping("/cancelAlarm/{id}")
    public R cancelAlarm(@ApiParam("警报编号")@PathVariable("id") String id){
        alarmService.removeAlarm ( id );
        return R.ok ().data ( "info","移除成功" );
    }
    @ApiOperation ( "获得警报信息" )
    @GetMapping("/getAlarm/{userid}")
    public R getAlarm(@ApiParam("用户编号")@PathVariable("userid") String userid){
        TbAlarm alarm = alarmService.getAlarm ( userid );
        return R.ok ().data ( "alarm",alarm );
    }
    @ApiOperation ( "发布警告" )
    @PostMapping("/sendAlarm")
    public R sendAlarm(@RequestBody@ApiParam("警告映射类") AlarmVo alarmVo){
        TbAlarm alarm = new TbAlarm ( alarmVo );
        alarmService.save ( alarm );
        return R.ok ().data ( "info","发布成功" );
    }
    @ApiOperation ( "获得所有警告(仅供测试)" )
    @GetMapping("/alarms")
    public R alarms(){
        return R.ok ().data ( "data",alarmService.getAlarms () );
    }
    @ApiOperation ( "根据用户获得警告历史" )
    @GetMapping("/getAlarms/{id}")
    public R getAlarms(@PathVariable("id")@ApiParam("用户id")String id){
        List<TbAlarm> alarmList = alarmService.getHistory ( id );
        return R.ok ().data ( "data",alarmList );
    }
}

