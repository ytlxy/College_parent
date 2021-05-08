package com.at.eduservice.service.impl;

import com.at.eduservice.entity.EduChapter;
import com.at.eduservice.entity.EduVideo;
import com.at.eduservice.entity.chapter.ChapterVo;
import com.at.eduservice.entity.chapter.ViedoVo;
import com.at.eduservice.mapper.EduChapterMapper;
import com.at.eduservice.service.EduChapterService;
import com.at.eduservice.service.EduVideoService;
import com.at.serviceBash.exceptionhandler.BoomException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author :)
 * @since 2021-04-21
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        ArrayList<ChapterVo> finalChapterVos=new ArrayList<>();
        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters=baseMapper.selectList(wrapper);
        QueryWrapper<EduVideo> wrapper1=new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        List<EduVideo> eduVideos=eduVideoService.list(wrapper1);
        for (int i=0;i<eduChapters.size();i++){
            EduChapter chapter=eduChapters.get(i);
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            finalChapterVos.add(chapterVo);
            ArrayList<ViedoVo> finalVideoVos=new ArrayList<>();
            for (int j=0;j<eduVideos.size();j++){
                EduVideo video=eduVideos.get(j);
                if (chapter.getId().equals(video.getChapterId())){
                    ViedoVo vo=new ViedoVo();
                    BeanUtils.copyProperties(video,vo);
                    finalVideoVos.add(vo);
                }
            }
            chapterVo.setChildren(finalVideoVos);
        }
        return finalChapterVos;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(wrapper);
        if (count > 0){
            throw new BoomException(20001,"章节中有小结无法删除");
        }else {
            int delete=baseMapper.deleteById(chapterId);
            return delete>0;
        }
    }
}
