package com.at.vod.controller;

import com.at.commonUtils.R;
import com.at.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("/uploadAlVideo")
    public R uploadAlVideo(MultipartFile file){
        String s = vodService.uploadVideoAly(file);
        return R.ok().data("videoId",s);
    }

    @DeleteMapping("/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id){
        vodService.removeAliyunVideoById(id);
        return R.ok();
    }

    @DeleteMapping("/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }
}
