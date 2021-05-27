package com.at.eduservice.service.impl;

import com.at.eduservice.entity.EduTeacher;
import com.at.eduservice.mapper.EduTeacherMapper;
import com.at.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author :)
 * @since 2021-03-23
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Cacheable(key = "'selectHotTeacher'", value = "teacher")
    @Override
    public List<EduTeacher> selectTeacherList() {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        List<EduTeacher> eduTeachers = baseMapper.selectList(wrapper);
        return eduTeachers;
    }
}
