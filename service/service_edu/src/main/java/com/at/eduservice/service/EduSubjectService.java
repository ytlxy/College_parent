package com.at.eduservice.service;

import com.at.eduservice.entity.EduSubject;
import com.at.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author :)
 * @since 2021-04-16
 */
public interface EduSubjectService extends IService<EduSubject> {
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);
    public List<OneSubject> getAllOneTwoSubject();
}
