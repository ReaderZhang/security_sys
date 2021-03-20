package com.qqz.security_sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqz.security_sys.VO.MessageDivide;
import com.qqz.security_sys.entity.TbAlarm;
import com.qqz.security_sys.entity.TbMessage;
import com.qqz.security_sys.mapper.TbHomerMapper;
import com.qqz.security_sys.mapper.TbMessageMapper;
import com.qqz.security_sys.service.TbMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
@Service
public class TbMessageServiceImpl extends ServiceImpl<TbMessageMapper, TbMessage> implements TbMessageService {
    @Autowired
    private TbMessageMapper messageMapper;

    @Override
    public Map<Integer, MessageDivide> getMessage(String id){
        QueryWrapper<TbMessage> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "userid",id );
        wrapper.orderByDesc ( "gmt_created" );
        List<TbMessage> messages = messageMapper.selectList ( wrapper );
        List<TbMessage> messages1 = new ArrayList<> ();
        List<TbMessage> messages2 = new ArrayList<> ();
        int count1 = 0;
        int count2 = 0;
        for (TbMessage message:messages){
            switch (message.getType ()){
                case 1: {
                    messages1.add ( message );
                    if (message.getIsRead ()==0){
                        message.setIsRead ( 1 );
                        messageMapper.updateById ( message );
                        count1++;
                    }
                    break;
                }
                case 2:{
                    messages2.add ( message );
                    if (message.getIsRead ()==0){
                        message.setIsRead ( 1 );
                        messageMapper.updateById ( message );
                        count2++;
                    }
                    break;
                }
                default:{

                }
            }
        }
        MessageDivide messageDivide1 = new MessageDivide (messages1,count1);
        MessageDivide messageDivide2 = new MessageDivide (messages2,count2);
        Map<Integer,MessageDivide> map = new HashMap<> ();
        map.put ( 1,messageDivide1 );
        map.put ( 2,messageDivide2 );
        return map;
    }

    @Override
    public Integer getUnread(Integer typeid,String userid) {
        QueryWrapper<TbMessage> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "is_read", 0);
        wrapper.eq ( "type",typeid );
        wrapper.eq ( "homerid",userid );
        return messageMapper.selectCount ( wrapper );
    }

    @Override
    public List<TbMessage> getAllMessages(String id,Integer type) {
        QueryWrapper<TbMessage> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "homerid",id );
        wrapper.eq ( "type",type );
        List<TbMessage> messages = messageMapper.selectList ( wrapper );
        for (TbMessage message:messages){
            if (message.getIsRead ()==0){
                message.setIsRead ( 1 );
                messageMapper.updateById ( message );
            }
        }
        return messages;
    }

    @Override
    public List<TbMessage> getLastMessage(String id) {
        QueryWrapper<TbMessage> wrapper1 = new QueryWrapper<> ();
        QueryWrapper<TbMessage> wrapper2 = new QueryWrapper<> ();
        wrapper1.orderByDesc ( "gmt_created" );
        wrapper2.orderByDesc ( "gmt_created" );
        wrapper1.eq ( "type",1 );
        wrapper2.eq ( "type",2 );
        List<TbMessage> messages = new ArrayList<>();
        messages.add ( messageMapper.selectOne ( wrapper1 ) );
        messages.add ( messageMapper.selectOne ( wrapper2 ) );
        return messages;
    }

    @Override
    public List<TbMessage> getMessages() {
        QueryWrapper<TbMessage> wrapper = new QueryWrapper<> ();
        return messageMapper.selectList ( wrapper );
    }
}
