package com.qqz.security_sys;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqz.security_sys.Common.Utils.md5DecodeUtil;
import com.qqz.security_sys.Common.Vadilator.SmsRandomCode;
import com.qqz.security_sys.entity.TbAlarm;
import com.qqz.security_sys.entity.TbHomer;
import com.qqz.security_sys.mapper.TbHomerMapper;
import com.qqz.security_sys.service.TbAlarmService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@SpringBootTest
class SecuritySysApplicationTests {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private TbHomerMapper homerMapper;
    @Autowired
    private TbAlarmService alarmService;
    @Test
    void contextLoads() {
        System.out.println ("========================================");
        redisTemplate.opsForValue ().set ( "9999","9999" );
    }
    @Test
    void md5test(){
        String password = "123";
        String code = md5DecodeUtil.decode (password);
        System.out.println (code);
    }
    @Test
    void Tbhomer(){
        QueryWrapper<TbHomer> wrapper = new QueryWrapper<> ();
//        wrapper.eq ( "phone","13417249167" );
//        wrapper.eq ( "password","7aa1b25ee186bcb8996648532c4da5ca" );
//        int count = homerMapper.selectCount ( wrapper );
//        System.out.println (count);
    }
    @Test
    void TbAlarm(){
        List<TbAlarm> alarmList = alarmService.getHistory ( "1362675537353461762" );
        System.out.println (alarmList);
//        TbAlarm tbAlarm = new TbAlarm (  );
//        tbAlarm.setAddress ( "广东省湛江市麻章区海大路1号" );
//        tbAlarm.setLocation ( "卧室" );
//        tbAlarm.setUserid ("1362675537353461762");
//        alarmService.save ( tbAlarm );
    }
    @Test
    void oss() throws FileNotFoundException {
        OSS ossClient = new OSSClientBuilder ().build ( "https://oss-cn-beijing.aliyuncs.com","xxxxxxxxxx","xxxxxxxxxxx" );
        File file = new File ( "C:\\Users\\qqz\\Pictures\\ss.jpg" );
        InputStream inputStream = new FileInputStream(file);
        ossClient.putObject ( "oss-qqz-security-sys","test.jpg",inputStream);
        ossClient.shutdown ();
    }
}
