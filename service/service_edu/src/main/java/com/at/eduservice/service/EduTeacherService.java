package com.at.eduservice.service;

import com.at.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author :)
 * @since 2021-03-23
 */
public interface EduTeacherService extends IService<EduTeacher> {
    public List<EduTeacher> selectTeacherList();
}
