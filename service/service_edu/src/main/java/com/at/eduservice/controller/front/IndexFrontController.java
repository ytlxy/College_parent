package com.at.eduservice.controller.front;

import com.at.commonUtils.R;
import com.at.eduservice.entity.EduCourse;
import com.at.eduservice.entity.EduTeacher;
import com.at.eduservice.service.EduCourseService;
import com.at.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/index")
    public R index(){
        List<EduCourse> list = eduCourseService.selectList();
        List<EduTeacher> list1 = eduTeacherService.selectTeacherList();
        return R.ok().data("courseList",list).data("teacherList",list1);
    }
}
