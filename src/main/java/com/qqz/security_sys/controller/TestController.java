//package com.qqz.security_sys.controller;/*
//@Author qqz
//@create 2021-02-22  17:38
//*/
//
//import com.qqz.security_sys.Common.Utils.OSSUtil;
//import com.qqz.security_sys.Common.response.R;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//public class TestController {
//    @PostMapping("/testFileUpload")
//    public R upload(@RequestParam("file")MultipartFile file){
//        if (file==null){
//            return R.ClientError ().data ( "info","上传文件为空" );
//        }
//        String filename = file.getOriginalFilename ();
//        String url = null;
//        if (!filename.contains ( ".jpg" )||!filename.contains ( "png" )){
//            try {
//                url = OSSUtil.upload ( filename,file.getInputStream () );
//            } catch (IOException e) {
//                e.printStackTrace ();
//            }
//        }
//        return R.ok ().data ( "info","上传成功" ).data ( "url",url );
//    }
//}
