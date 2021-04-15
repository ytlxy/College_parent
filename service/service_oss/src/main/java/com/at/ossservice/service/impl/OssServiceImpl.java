package com.at.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.at.ossservice.service.OssService;
import com.at.ossservice.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            String a=UUID.randomUUID().toString().replaceAll("-","");
            String date=new DateTime().toString("yyyy/MMdd");
            String fileName= date+"/"+a+file.getOriginalFilename();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file.getInputStream());
            ossClient.putObject(putObjectRequest);
            ossClient.shutdown();
            String url="http://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
