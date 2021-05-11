package com.at.eduservice.service;

import com.at.eduservice.entity.EduChapter;
import com.at.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author :)
 * @since 2021-04-21
 */
public interface EduChapterService extends IService<EduChapter> {
    public List<ChapterVo> getChapterVideoByCourseId(String courseId);
    public boolean deleteChapter(String chapterId);
    public void removeChapterByCourseId(String id);
}
