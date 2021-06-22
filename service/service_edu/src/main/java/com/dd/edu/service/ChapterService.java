package com.dd.edu.service;

import com.dd.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dd.edu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-20
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getAllChapter(String course_id);
}
