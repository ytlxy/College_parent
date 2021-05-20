package com.at.eduservice.controller;


import com.at.commonUtils.R;
import com.at.eduservice.client.VodClient;
import com.at.eduservice.entity.EduVideo;
import com.at.eduservice.service.EduVideoService;
import com.at.serviceBash.exceptionhandler.BoomException;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author :)
 * @since 2021-04-21
 */
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        boolean flag = eduVideoService.save(eduVideo);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteVideo(@PathVariable String id) {
        EduVideo video = eduVideoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if (!StringUtils.isEmptyOrWhitespaceOnly(videoSourceId)){
            R r = vodClient.removeAlyVideo(videoSourceId);
            if (r.getCode() == 20001){
                throw new BoomException(20001,"视频删除失败");
            }
        }
        eduVideoService.removeById(id);
        return R.ok();
    }

    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        boolean flag = eduVideoService.updateById(eduVideo);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("/getVideoById/{id}")
    public R getVideoById(@PathVariable String id) {
        EduVideo byId = eduVideoService.getById(id);
        return R.ok().data("video", byId);
    }
}
