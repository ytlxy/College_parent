package com.at.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.at.serviceBash.exceptionhandler.BoomException;
import com.at.vod.service.VodService;
import com.at.vod.utils.ConstantVodUtils;
import com.at.vod.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {


    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            InputStream stream=file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_KEYSECRET, title, fileName,stream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
                System.out.println(videoId);
            } else {
                videoId = response.getVideoId();
            }
            return videoId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void removeAliyunVideoById(String id) {
        try {
            DefaultAcsClient client= InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_KEYSECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            DeleteVideoResponse response = client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
            throw new BoomException(20001, "删除视频失败");
        }
    }

    @Override
    public void removeMoreAlyVideo(List<String> videoIdList) {
        try {
            DefaultAcsClient client= InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_KEYSECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            String s = StringUtils.join(videoIdList.toArray(), ",");
            request.setVideoIds(s);
            DeleteVideoResponse response = client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
            throw new BoomException(20001, "删除视频失败");
        }
    }
}
