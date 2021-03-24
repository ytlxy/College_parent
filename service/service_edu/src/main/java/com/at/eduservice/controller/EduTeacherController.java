package com.at.eduservice.controller;


import com.at.commonUtils.R;
import com.at.eduservice.entity.EduTeacher;
import com.at.eduservice.entity.vo.TeacherQuery;
import com.at.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author :)
 * @since 2021-03-23
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "查询所有讲师")
    @GetMapping("/findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/page/{current}/{size}")
    public R pageTeacher(@PathVariable int current,@PathVariable int size){
        Page<EduTeacher> page=new Page<>(current,size);
        eduTeacherService.page(page,null);
        long total=page.getTotal();
        List<EduTeacher> list=page.getRecords();
        return R.ok().data("Total",total).data("findPage",list);
    }

    @PostMapping("/pageCondition/{current}/{size}")
    public R pageTeacherCondition(@PathVariable Long current, @PathVariable Long size,@RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> page=new Page<>(current,size);
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (name!=null){
            wrapper.like("name",name);
        }if (level!=null){
            wrapper.eq("level",level);
        }if (begin!=null){
            wrapper.ge("gmt_create",begin);
        }if (end!=null){
            wrapper.le("gmt_create",end);
        }
        eduTeacherService.page(page,wrapper);
        long total=page.getTotal();
        List<EduTeacher> list=page.getRecords();
        return R.ok().data("total",total).data("list",list);
    }

    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/getTeacher/{id}")
    public R getTeacher(@PathVariable Long id){
        EduTeacher byId = eduTeacherService.getById(id);
        return R.ok().data("Teacher",byId);
    }

    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

