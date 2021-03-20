package com.qqz.security_sys.service;

import com.qqz.security_sys.entity.TbAlarm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
public interface TbAlarmService extends IService<TbAlarm> {
    public TbAlarm getAlarm(String userid);
    public boolean removeAlarm(String id);
    public List<TbAlarm> getAlarms();
    public List<TbAlarm> getHistory(String userid);
}
