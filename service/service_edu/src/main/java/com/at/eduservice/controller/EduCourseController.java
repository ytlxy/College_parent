package com.at.eduservice.controller;


import com.at.commonUtils.R;
import com.at.eduservice.entity.EduCourse;
import com.at.eduservice.entity.vo.CourseInfoVo;
import com.at.eduservice.entity.vo.CoursePublishVo;
import com.at.eduservice.entity.vo.CourseQuery;
import com.at.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author :)
 * @since 2021-04-21
 */
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("/getCourseList")
    public R getCourseList() {
        List<EduCourse> list = eduCourseService.list();
        return R.ok().data("list", list);
    }

    @PostMapping("/page/{page}/{size}")
    public R getCourseListPage(@PathVariable int page, @PathVariable int size, @RequestBody(required = false) CourseQuery courseQuery) {
        Page<EduCourse> page1 = new Page<>(page, size);
        eduCourseService.pageQuery(page1, courseQuery);
        Long total = page1.getTotal();
        List<EduCourse> list = page1.getRecords();
        return R.ok().data("total", total).data("list", list);
    }

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    @GetMapping("/getCourseInfoById/{courseId}")
    public R getCourseInfoById(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @GetMapping("/getPublishCourse/{id}")
    public R getPublishCourse(@PathVariable String id) {
        CoursePublishVo publishCourseInfo = eduCourseService.getPublishCourseInfo(id);
        return R.ok().data("publishCourse", publishCourseInfo);
    }

    @PostMapping("/publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        boolean a = eduCourseService.updateById(eduCourse);
        if (a) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/removeCourseById/{id}")
    public R removeCourseById(@PathVariable String id) {
        boolean flag = eduCourseService.removeCourse(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}
