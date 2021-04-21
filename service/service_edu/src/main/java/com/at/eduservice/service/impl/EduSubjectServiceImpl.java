package com.at.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.at.eduservice.entity.EduSubject;
import com.at.eduservice.entity.excel.SubjectData;
import com.at.eduservice.entity.subject.OneSubject;
import com.at.eduservice.entity.subject.TwoSubject;
import com.at.eduservice.listener.SubjectExcelListener;
import com.at.eduservice.mapper.EduSubjectMapper;
import com.at.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapper);
        QueryWrapper wrapperTwo=new QueryWrapper();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList= this.list(wrapperTwo);
        List<OneSubject> finalSubjectList=new ArrayList<>();
        for (int i=0;i<oneSubjectList.size();i++){
            EduSubject eduSubject=oneSubjectList.get(i);
            OneSubject oneSubject=new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);
            List<TwoSubject> twofinalSubjectList=new ArrayList<>();
            for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject eduSubject2=twoSubjectList.get(j);
                if (eduSubject2.getParentId().equals(oneSubject.getId())){
                    TwoSubject twoSubject=new TwoSubject();
                    BeanUtils.copyProperties(eduSubject2,twoSubject);
                    twofinalSubjectList.add(twoSubject);
                }
                oneSubject.setChildren(twofinalSubjectList);
            }
        }
        return finalSubjectList;
    }
}
