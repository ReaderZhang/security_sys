package com.qqz.security_sys.service;

import com.qqz.security_sys.VO.HomberLogin;
import com.qqz.security_sys.entity.TbHomer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-10
 */
public interface TbHomerService extends IService<TbHomer> {
    public TbHomer Login(HomberLogin homberLogin);

    public boolean register(TbHomer homer);

    public TbHomer findHomerByPhone(String phone);

    public List<TbHomer> getHomers();
}
