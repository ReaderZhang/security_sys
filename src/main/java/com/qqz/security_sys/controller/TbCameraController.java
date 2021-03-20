package com.qqz.security_sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qqz.security_sys.Common.Utils.OSSUtil;
import com.qqz.security_sys.Common.response.R;
import com.qqz.security_sys.VO.CameraVo;
import com.qqz.security_sys.entity.TbCamera;
import com.qqz.security_sys.service.TbCameraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-26
 */
@CrossOrigin
@RestController
@RequestMapping("/security_sys/tb-camera")
@Api(value = "摄像头相关接口",tags = {"摄像头"})
public class TbCameraController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private TbCameraService cameraService;
    @ApiOperation( "上传摄像头图像" )
    @PostMapping("/uploadAvator/{id}")
    public R uploadAvator(@RequestParam("file") MultipartFile file, @PathVariable("id")String id){
        if (file==null){
            return R.ClientError ().data ( "info","上传文件为空" );
        }
        String filename = file.getOriginalFilename ();
        String url = null;
        if (!filename.contains ( ".jpg" )||!filename.contains ( "png" )){
            try {
                url = OSSUtil.upload ( filename,file.getInputStream () );
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        assert url != null;
        redisTemplate.opsForValue ().set ( id+"ca",url );
        return R.ok ().data ( "info","上传成功" );
    }
    @ApiOperation ( "补充摄像头信息" )
    @PostMapping("/addCamera")
    public R addCamera(@RequestBody CameraVo cameraVo){
        TbCamera camera = new TbCamera ();
        camera.setAddress ( cameraVo.getAddress () );
        camera.setAvator ( redisTemplate.opsForValue ().get(cameraVo.getHomerid ()+"ca") );
        camera.setType ( cameraVo.getType () );
        camera.setSort ( cameraVo.getSort () );
        camera.setHomerId ( cameraVo.getHomerid () );
        cameraService.save ( camera );
        return R.ok ().data ( "info","补充成功" );
    }
    @ApiOperation ( "获取摄像头信息" )
    @GetMapping("/getUserCameras/{id}")
    public R cameras(@PathVariable("id")String id){
        QueryWrapper<TbCamera> wrapper = new QueryWrapper<> ();
        wrapper.eq ( "homer_id",id );
        List<TbCamera> cameraList = cameraService.list (wrapper);
        return R.ok ().data ( "data",cameraList );
    }
    @ApiOperation ( "修改摄像头信息" )
    @PostMapping("/updateCamera/{id}")
    public R updateCamera(@RequestBody CameraVo cameraVo,@PathVariable("id")@ApiParam("摄像头id") String id){
        TbCamera camera = cameraService.getById ( id );
        camera.setAddress ( cameraVo.getAddress () );
        camera.setAvator ( redisTemplate.opsForValue ().get(id+"ca") );
        camera.setType ( cameraVo.getType () );
        camera.setSort ( cameraVo.getSort () );
        cameraService.saveOrUpdate ( camera );
        return R.ok ().data ( "info","修改成功" );
    }
    @ApiOperation ( "获得单个摄像头信息" )
    @GetMapping("/getOneCamere/{id}")
    public R getOneCamera(@PathVariable("id")String id){
        return R.ok ().data ( "data",cameraService.getById ( id ) );
    }
    @ApiOperation ( "删除摄像头" )
    @DeleteMapping("/deleteCamera/{id}")
    public R deleteCamera(@PathVariable("id")String id){
        cameraService.removeById ( id );
        return R.ok ().data ( "info","删除成功" );
    }
    @ApiOperation ( "获得所有摄像头(仅供测试)" )
    @GetMapping("/cameras")
    public R cameras(){
        QueryWrapper<TbCamera> wrapper = new QueryWrapper<> ();
        return R.ok ().data ( "data",cameraService.list ( wrapper ) );
    }

}

