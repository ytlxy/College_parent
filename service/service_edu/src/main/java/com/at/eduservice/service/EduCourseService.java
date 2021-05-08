package com.at.eduservice.service;

import com.at.eduservice.entity.EduCourse;
import com.at.eduservice.entity.vo.CourseInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author :)
 * @since 2021-04-21
 */
public interface EduCourseService extends IService<EduCourse> {
    public String saveCourseInfo(CourseInfoVo courseInfoVo);
    public CourseInfoVo getCourseInfo(String courseId);
    public void updateCourseInfo(CourseInfoVo courseInfoVo);
}
