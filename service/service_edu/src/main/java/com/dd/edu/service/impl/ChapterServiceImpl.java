package com.dd.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.edu.entity.Chapter;
import com.dd.edu.entity.Video;
import com.dd.edu.entity.chapter.ChapterVo;
import com.dd.edu.entity.chapter.VideoVo;
import com.dd.edu.mapper.ChapterMapper;
import com.dd.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dd.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-20
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getAllChapter(String course_id) {
        //1.用课程id查询所有的章节信息
        QueryWrapper chapterWrapper=new QueryWrapper();
        chapterWrapper.eq("course_id",course_id);
        List<Chapter> chapters=baseMapper.selectList(chapterWrapper);


        //2.用章节id查询所有的小节信息
        QueryWrapper videoWrapper=new QueryWrapper();
        videoWrapper.eq("course_id",course_id);
       List<Video> videos=videoService.list(videoWrapper);


        List<ChapterVo> chapterVoList= new ArrayList<>();

        //3.将章节信息遍历存在数组中（章节格式化）
        for (int i = 0; i <chapters.size() ; i++) {
            Chapter chapter=chapters.get(i);
            ChapterVo chapterVo=new ChapterVo();
            chapterVo.setId(chapter.getId());
            chapterVo.setTitle(chapter.getTitle());
            List<VideoVo> videoVoList=new ArrayList<>();//用于存储章节的信息

            //4.将小节信息分类存放到章节的children中（小节格式化，并存放到章节的children）
            for (int j = 0; j <videos.size() ; j++) {
                Video video=videos.get(j);
                if (video.getChapterId().equals(chapter.getId())){
                    VideoVo videoVo=new VideoVo();
                    videoVo.setId(video.getId());
                    videoVo.setTitle(video.getTitle());
                    videoVoList.add(videoVo);
                }

            }
            chapterVo.setChildren(videoVoList);//将小节数组添加到children的对象中
            chapterVoList.add(chapterVo);//将章节数据添加到chapterVoList
        }

        return chapterVoList;
    }
}
