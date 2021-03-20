package com.qqz.security_sys.Common.Utils;/*
@Author qqz
@create 2021-02-22  15:45
*/

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OSSUtil {
    public final static String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    public final static String accesskey = "LTAI4GJ5JjbF68bv6xz9x7fS";
    public final static String secret = "cJQLuAuwWGcS7sZWFmBXj2X8pe5Hr1";
    public final static String bucketname = "oss-qqz-security-sys";
    public final static String domain = "https://oss-qqz-security-sys.oss-cn-beijing.aliyuncs.com/";
    public final static SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMMddhhmmss");
    public static OSS getOssClient(){
        return new OSSClientBuilder ().build ( endpoint,accesskey,secret );
    }
    public static String upload(String fileName, InputStream inputStream){
        OSS client = getOssClient();
        String newName = randomImage ( fileName );
        try {
            client.putObject ( bucketname , newName , inputStream );
        }catch (Exception e){
            e.printStackTrace ();
        }finally {
            client.shutdown ();
        }
        return domain+newName;
    }
    public static String randomImage(String fileName){
        String last = fileName.substring ( fileName.indexOf ( "." ),fileName.length () );
        String original = fileName.substring ( 0,fileName.indexOf ( "." ) );
        int randomstring = (int)(Math.random ()*1000);
        Date date = new Date ();
        return original+sdf.format(date)+randomstring+last;
    }
}