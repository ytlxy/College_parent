package com.at.eduservice.service.impl;

import com.at.eduservice.entity.EduChapter;
import com.at.eduservice.entity.EduCourse;
import com.at.eduservice.entity.EduCourseDescription;
import com.at.eduservice.entity.vo.CourseInfoVo;
import com.at.eduservice.entity.vo.CoursePublishVo;
import com.at.eduservice.entity.vo.CourseQuery;
import com.at.eduservice.mapper.EduCourseMapper;
import com.at.eduservice.service.EduChapterService;
import com.at.eduservice.service.EduCourseDescriptionService;
import com.at.eduservice.service.EduCourseService;
import com.at.eduservice.service.EduVideoService;
import com.at.serviceBash.exceptionhandler.BoomException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author :)
 * @since 2021-04-21
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            throw new BoomException(20001, "添加课程失败");
        }
        String cid = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new BoomException(20001, "修改课程信息失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        if (title != null) {
            wrapper.like("title", title);
        }
        if (status != null){
            wrapper.eq("status",status);
        }
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageParam,wrapper);
    }

    @Override
    public boolean removeCourse(String id) {
        eduChapterService.removeChapterByCourseId(id);
        eduVideoService.removeVideoByCourseId(id);
        eduCourseDescriptionService.removeCourseDescriptionByCourseId(id);
        int result=baseMapper.deleteById(id);
        if (result >= 1){
            return true;
        }else{
            throw new BoomException(20001,"删除失败");
        }
    }
}
