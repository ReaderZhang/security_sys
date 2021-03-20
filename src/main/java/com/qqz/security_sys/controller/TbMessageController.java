package com.qqz.security_sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqz.security_sys.Common.response.R;
import com.qqz.security_sys.VO.MessageDivide;
import com.qqz.security_sys.VO.MessageUpdateVo;
import com.qqz.security_sys.VO.MessageVo;
import com.qqz.security_sys.entity.TbMessage;
import com.qqz.security_sys.service.TbMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
@Api(value = "消息接口",tags = {"消息"})
@RequestMapping("/security_sys/tb-message")
public class TbMessageController {
    @Autowired
    private TbMessageService messageService;
    @ApiOperation ( value = "获得留言信息")
    @GetMapping("/getMessage/{id}")
    public R getMessage(@PathVariable("id")@ApiParam("用户编号") String id){
        QueryWrapper<TbMessage> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "homerid",id );
        wrapper.orderByDesc ( "gmt_created" );
        List<TbMessage> messages = messageService.list (wrapper);
        List<TbMessage> messages1 = messageService.list (wrapper);
        for (TbMessage message : messages){
            message.setIsRead ( 1 );
        }
        messageService.saveOrUpdateBatch ( messages );
        return R.ok ().data ( "data",messages1 );
    }
    @ApiOperation ( value = "获得留言信息(new)")
    @GetMapping("/getMessaging/{id}")
    public R getMessaging(@PathVariable("id")@ApiParam("用户编号") String id){
        QueryWrapper<TbMessage> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "homerid",id );
        wrapper.orderByDesc ( "gmt_created" );
        List<TbMessage> messages1 = messageService.list (wrapper);
        messageService.saveOrUpdateBatch ( messages1 );
        return R.ok ().data ( "data",messages1 );
    }
    @ApiOperation ( value = "修改留言信息")
    @PostMapping("/updatemessage/{id}")
    public R updateMessage(@RequestBody MessageUpdateVo messageUpdateVo,@PathVariable("id")String id){
        TbMessage message = messageService.getById ( id );
        message.setType ( messageUpdateVo.getType () );
        message.setContent ( messageUpdateVo.getContent () );
        message.setTitle ( messageUpdateVo.getTitle () );
        message.setIsRead ( messageUpdateVo.getIs_read () );
        messageService.saveOrUpdate ( message );
        return R.ok ().data ( "info","修改成功" ).data ( "data",message );
    }
    @ApiOperation ( value = "删除留言信息")
    @DeleteMapping("/deletemessage/{id}")
    public R deleteMessage(@PathVariable("id")String id){
        messageService.removeById ( id );
        return R.ok ().data ( "info","删除成功" );
    }
    @ApiOperation ( value = "进行留言")
    @PostMapping("/saveMessage")
    public R saveMessage(@RequestBody MessageVo messageVo){
        TbMessage message = new TbMessage (  );
        message.setTitle ( messageVo.getTitle () );
        message.setContent (  messageVo.getContent () );
        message.setHomerid ( messageVo.getUserid () );
        message.setType ( messageVo.getType () );
        messageService.save ( message );
        return R.ok ().data ( "info","添加成功" );
    }
    @ApiOperation ( value = "查询最后一条记录和未读信息")
    @PostMapping("/getMessage/info/{userid}")
    public R getMessageInfo(@ApiParam("用户编号")@PathVariable("userid") String userid){
        int count1 = messageService.getUnread ( 1,userid );
        int count2 = messageService.getUnread ( 2,userid );
        List<TbMessage> message = messageService.getLastMessage ( userid );
        List<Integer> counts = new ArrayList<> ();
        counts.add ( count1 );
        counts.add ( count2 );
        return R.ok ().data ( "messages",message ).data ( "count",counts );
    }
    @ApiOperation ( "获得所有消息(仅供测试)" )
    @GetMapping("/messages")
    public R messages(){
        return R.ok ().data ( "data",messageService.getMessages () );
    }

}

