package com.qqz.security_sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqz.security_sys.entity.TbAlarm;
import com.qqz.security_sys.mapper.TbAlarmMapper;
import com.qqz.security_sys.service.TbAlarmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
@Service
public class TbAlarmServiceImpl extends ServiceImpl<TbAlarmMapper, TbAlarm> implements TbAlarmService {

    @Autowired
    private TbAlarmMapper alarmMapper;
    @Override
    public TbAlarm getAlarm(String userid) {
        QueryWrapper<TbAlarm> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "userid",userid );
        return alarmMapper.selectOne ( wrapper );
    }

    @Override
    public boolean removeAlarm(String id) {
        QueryWrapper<TbAlarm> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "userid",id );
        alarmMapper.delete ( wrapper );
        return true;
    }

    @Override
    public List<TbAlarm> getAlarms() {
        QueryWrapper<TbAlarm> wrapper = new QueryWrapper<> ();
        return alarmMapper.selectList ( wrapper );
    }

    @Override
    public List<TbAlarm> getHistory(String userid) {
        return alarmMapper.SelectAlarmsAll ( userid );
    }

}
