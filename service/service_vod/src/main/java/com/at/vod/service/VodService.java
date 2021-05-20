package com.at.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    public String uploadVideoAly(MultipartFile file);
    public void removeAliyunVideoById(String id);
    public void removeMoreAlyVideo(List<String> videoIdList);
}
