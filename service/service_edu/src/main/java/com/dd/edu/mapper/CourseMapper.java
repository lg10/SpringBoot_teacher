package com.dd.edu.mapper;

import com.dd.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dd.edu.entity.vo.CoursePublistVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-06-20
 */
public interface CourseMapper extends BaseMapper<Course> {
    public CoursePublistVo getPublicCourseInfo(String id);

}
