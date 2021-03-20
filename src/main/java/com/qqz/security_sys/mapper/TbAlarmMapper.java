package com.qqz.security_sys.mapper;

import com.qqz.security_sys.entity.TbAlarm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-02-20
 */
public interface TbAlarmMapper extends BaseMapper<TbAlarm> {
    @Select ( "        select address,location,action,userid from tb_alarm where userid=#{userid}" )
    List<TbAlarm> SelectAlarmsAll(@Param ( "userid" ) String userid);
}
