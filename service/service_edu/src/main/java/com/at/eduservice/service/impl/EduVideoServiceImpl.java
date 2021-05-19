package com.at.eduservice.service.impl;

import com.at.eduservice.client.VodClient;
import com.at.eduservice.entity.EduVideo;
import com.at.eduservice.mapper.EduVideoMapper;
import com.at.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author :)
 * @since 2021-04-21
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String id) {
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",id);
        wrapper.select("Video_source_id");
        List<EduVideo> list = baseMapper.selectList(wrapper);
        List<String> videoIds=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            EduVideo video = list.get(i);
            String videoSourceId = video.getVideoSourceId();
            videoIds.add(videoSourceId);
        }
        if (videoIds.size()>0){
            vodClient.deleteBatch(videoIds);
        }
        QueryWrapper<EduVideo> wrapper1=new QueryWrapper<>();
        wrapper1.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
