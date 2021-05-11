package com.at.eduservice.service.impl;

import com.at.eduservice.entity.EduCourseDescription;
import com.at.eduservice.mapper.EduCourseDescriptionMapper;
import com.at.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author :)
 * @since 2021-04-21
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public void removeCourseDescriptionByCourseId(String id) {
        QueryWrapper<EduCourseDescription> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        baseMapper.delete(wrapper);
    }
}
