package com.at.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.at.eduservice.entity.EduSubject;
import com.at.eduservice.entity.excel.SubjectData;
import com.at.eduservice.listener.SubjectExcelListener;
import com.at.eduservice.mapper.EduSubjectMapper;
import com.at.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author :)
 * @since 2021-04-16
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream in=file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
