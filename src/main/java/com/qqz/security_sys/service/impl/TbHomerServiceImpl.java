package com.qqz.security_sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqz.security_sys.Common.Utils.md5DecodeUtil;
import com.qqz.security_sys.VO.HomberLogin;
import com.qqz.security_sys.entity.TbHomer;
import com.qqz.security_sys.mapper.TbHomerMapper;
import com.qqz.security_sys.service.SmsService;
import com.qqz.security_sys.service.TbHomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-10
 */
@Service
public class TbHomerServiceImpl extends ServiceImpl<TbHomerMapper, TbHomer> implements TbHomerService {
    @Autowired
    TbHomerMapper homerMapper;
    @Autowired
    SmsService smsService;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Override
    public TbHomer Login(HomberLogin homberLogin) {
        String password = homberLogin.getPassword ();
        String phone = homberLogin.getPhone ();
        String md5password = md5DecodeUtil.decode (password);
        QueryWrapper<TbHomer> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "phone",phone );
        wrapper.eq ( "password",md5password );
        TbHomer homer = homerMapper.selectOne ( wrapper );
        if (homer!=null){
            redisTemplate.opsForValue ().set ( homer.getId (),"Login" );
        }
        return homer;
    }

    @Override
    public boolean register(TbHomer homer) {
        String phone = homer.getPhone ();
        QueryWrapper<TbHomer> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "phone",phone );
        TbHomer tbHomer = homerMapper.selectOne ( wrapper );
        if (tbHomer == null) {
            homerMapper.insert ( homer );
            return true;
        }
        return false;
    }
    @Override
    public TbHomer findHomerByPhone(String phone){
        QueryWrapper<TbHomer> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "phone",phone );
        return homerMapper.selectOne ( wrapper );
    }

    @Override
    public List<TbHomer> getHomers() {
        QueryWrapper<TbHomer> wrapper = new QueryWrapper<> ();
        return homerMapper.selectList ( wrapper );
    }

}
