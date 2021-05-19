package com.at.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

public class Testvod {
    public static void main(String[] args){
        String accessKeyId = "LTAI5t7HD6ccvJffdziMdVTN";
        String accessKeySecret = "oDYKN9OB0vphSS7kaY5rkt02qRTVMY";
        String title = "test2";
        String fileName = "C:\\Users\\hp\\Videos\\Captures\\test2.mp4";

        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        request.setPartSize(2 * 1024 * 1024L);
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    public static void getPlayAuth() throws ClientException {
        DefaultAcsClient defaultAcsClient = InitObject.initVodClient("LTAI5t7HD6ccvJffdziMdVTN", "oDYKN9OB0vphSS7kaY5rkt02qRTVMY");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId("d25f1bb0985840899e606276b4625143");
        response = defaultAcsClient.getAcsResponse(request);
        System.out.println(response.getPlayAuth());
    }

    public static void getPlayUrl() throws ClientException {
        DefaultAcsClient defaultAcsClient = InitObject.initVodClient("LTAI5t7HD6ccvJffdziMdVTN", "oDYKN9OB0vphSS7kaY5rkt02qRTVMY");
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        request.setVideoId("d25f1bb0985840899e606276b4625143");
        response = defaultAcsClient.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfos = response.getPlayInfoList();
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfos) {
            System.out.println(playInfo);
        }
    }
}
