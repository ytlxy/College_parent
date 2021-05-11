package com.at.eduservice.service;

import com.at.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author :)
 * @since 2021-04-21
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {
    public void removeCourseDescriptionByCourseId(String id);
}
