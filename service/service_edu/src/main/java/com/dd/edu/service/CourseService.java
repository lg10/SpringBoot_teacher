package com.dd.edu.service;

import com.dd.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dd.edu.entity.vo.CourseInfo;
import com.dd.edu.entity.vo.CoursePublistVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-20
 */
public interface CourseService extends IService<Course> {
    //添加课程信息
    String courseInfoSave(CourseInfo courseInfo);
    //根据id查询实体类CourseInfo中的信息
    CourseInfo getCourseInfoList(String id);
    //根据id 更新课程信息
    void updateCourceInfo(CourseInfo courseInfo);

    CoursePublistVo getPublishCourseInfo(String id);
}
