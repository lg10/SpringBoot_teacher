package com.dd.edu.controller;




import com.dd.edu.entity.chapter.ChapterVo;
import com.dd.edu.entity.chapter.VideoVo;
import com.dd.edu.service.ChapterService;
import com.dd.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-20
 */
@RestController
@RequestMapping("/service_edu/chapter")
@CrossOrigin
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/AllChapter/{course_id}")
    public R AllChapter(@PathVariable String course_id){
//        List<ChapterVo> ChapterList=chapterService.getAllChapter(course_id);
        List<ChapterVo> videoVoList=chapterService.getAllChapter(course_id);

        return  R.ok().data("list",videoVoList);

    }



}

