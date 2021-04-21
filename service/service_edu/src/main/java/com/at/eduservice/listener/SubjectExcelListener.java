package com.at.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.at.eduservice.entity.EduSubject;
import com.at.eduservice.entity.excel.SubjectData;
import com.at.eduservice.service.EduSubjectService;
import com.at.serviceBash.exceptionhandler.BoomException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new BoomException(20001, "文件数据为空");
        }
        EduSubject eduSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        System.out.println(eduSubject+"-------------------");
        if (eduSubject == null) {
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(eduSubject);
        }
        String pid = eduSubject.getId();
        EduSubject eduSubjectTwo = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if (eduSubjectTwo == null){
            eduSubjectTwo=new EduSubject();
            eduSubjectTwo.setParentId(pid);
            eduSubjectTwo.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(eduSubjectTwo);
        }
    }

    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name)
                .eq("parent_id","0");
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name)
                .eq("parent_id",pid);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
