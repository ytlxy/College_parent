package com.at.eduservice.service;

import com.at.eduservice.entity.EduCourse;
import com.at.eduservice.entity.vo.CourseInfoVo;
import com.at.eduservice.entity.vo.CoursePublishVo;
import com.at.eduservice.entity.vo.CourseQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
    public CoursePublishVo getPublishCourseInfo(String courseId);
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);
    public boolean removeCourse(String id);
    public List<EduCourse> selectList();
}
