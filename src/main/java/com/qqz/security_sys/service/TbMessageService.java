package com.qqz.security_sys.service;

import com.qqz.security_sys.VO.MessageDivide;
import com.qqz.security_sys.entity.TbAlarm;
import com.qqz.security_sys.entity.TbMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
public interface TbMessageService extends IService<TbMessage> {
    public Map<Integer, MessageDivide> getMessage(String id);
    public Integer getUnread(Integer typeid,String userid);
    public List<TbMessage> getAllMessages(String id,Integer type);
    public List<TbMessage> getLastMessage(String id);
    public List<TbMessage> getMessages();
}
