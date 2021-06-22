package com.dd.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.config.CustomException.CustomException;
import com.dd.edu.entity.Course;
import com.dd.edu.entity.CourseDescription;
import com.dd.edu.entity.vo.CourseInfo;
import com.dd.edu.entity.vo.CoursePublistVo;
import com.dd.edu.mapper.CourseMapper;
import com.dd.edu.service.CourseDescriptionService;
import com.dd.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin.javascript.navig.Link;

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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    //将CourseDescriptionService注入CourseService（原因：要将Description的数据插入edu_course_description表里）
    @Autowired
    CourseDescriptionService courseDescriptionService;
    //添加课程信息
    @Override
    public String courseInfoSave(CourseInfo courseInfo) {
        //courseInfo实例类包含两张表（course和courseDescription）的信息分别添加
        //向课程表里添加课程信息
        Course course=new Course();
        BeanUtils.copyProperties(courseInfo,course);
        int insert=baseMapper.insert(course);
        if (insert==0){
            throw new CustomException(20001,"课程信息添加错误");
        }
        //向课程简介表里添加课程简介
        try{
            CourseDescription courseDescription=new CourseDescription();
            courseDescription.setDescription(courseInfo.getDescription());
            courseDescriptionService.save(courseDescription);

        }catch (Exception e){
            throw new CustomException(20001,"课程简介添加错误");
        }
        return course.getId();
    }
    //根据id查询实体类CourseInfo中的信息
    @Override
    public CourseInfo getCourseInfoList(String id) {
        //获取课程信息
        Course courseList=baseMapper.selectById(id);

        //获得课程简介信息
        CourseDescription courseDescriptionList=courseDescriptionService.getById(id);
        //添加到courseInfoList
        CourseInfo courseInfo=new CourseInfo();

        BeanUtils.copyProperties(courseList,courseInfo);
        BeanUtils.copyProperties(courseDescriptionList,courseInfo);


        return courseInfo;
    }
    //根据id 更新课程信息
    @Override
    public void updateCourceInfo(CourseInfo courseInfo) {
        //courseInfo实例类包含两张表（course和courseDescription）的信息分别添加
        //向课程表里更新课程信息
        Course course=new Course();
        BeanUtils.copyProperties(courseInfo,course);
        boolean insert=this.updateById(course);
        if (!insert){
            throw new CustomException(20001,"课程信息更新错误");
        }
        //向课程简介表里更新课程简介
        try{
            CourseDescription courseDescription=new CourseDescription();
            courseDescription.setDescription(courseInfo.getDescription());
            courseDescriptionService.updateById(courseDescription);

        }catch (Exception e){
            throw new CustomException(20001,"课程简介更新错误");
        }
    }

    @Override
    public CoursePublistVo getPublishCourseInfo(String id) {
        CoursePublistVo coursePublistVo=baseMapper.getPublicCourseInfo(id);
        System.out.println(coursePublistVo);
        return coursePublistVo
                ;
    }


}
