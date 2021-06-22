package com.dd.edu.controller;


import com.dd.edu.client.OssClient;
import com.dd.edu.entity.vo.CourseInfo;
import com.dd.edu.entity.vo.CoursePublistVo;
import com.dd.edu.service.CourseService;
import com.dd.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
@RequestMapping("/service_edu/course_edu")
@CrossOrigin
@EnableDiscoveryClient//注册nacos
@EnableFeignClients
public class CourseController {
    @Autowired
   private CourseService courseService;


    //测试nacos删除oss中的图片
    @Autowired
    private OssClient ossClient;

//    @GetMapping("delFile/{fileName}")
//    private R delFileName(@PathVariable String fileName){
//        ossClient.delFile(fileName);
//        return R.ok();
//
//    }


    //添加课程信息
    @PostMapping("AddCourseInfo")
    private R AddCourseInfo(@RequestBody CourseInfo courseInfo){
        String id=courseService.courseInfoSave(courseInfo);
        return R.ok().data("id",id);
    }
//  根据id查询课程信息
    @GetMapping("GetCourseInfo")
    private R GetCourseInfo(@PathVariable String id){
        return R.ok().data("list",courseService.getById(id));
    }
    //根据id查询实体类CourseInfo中的信息
    @GetMapping("getCourseInfoId/{id}")
    private  R getCourseInfoId(@PathVariable String id){
        CourseInfo courseInfoList=courseService.getCourseInfoList(id);
        System.out.println(courseInfoList);

        return R.ok().data("list",courseInfoList);
    }
//  查询所有的课程信息
    @GetMapping("findAll")
    private R AllCourseInfo(){
        return  R.ok().data("list",courseService.list(null));
    }

   //根据id 更新课程信息
    @PostMapping("updateCourseInfo")
    private R updateCourseInfo(@RequestBody CourseInfo courseInfo){
       courseService.updateCourceInfo(courseInfo);
        return R.ok();
    }
//根据课程id，查询审核表内的数据
    @GetMapping("getPublishCourseInfo/{id}")
    private R getPublishCourseInfo(@PathVariable String id){
        CoursePublistVo coursePublistVo=courseService.getPublishCourseInfo(id);


        return R.ok().data("list",coursePublistVo);
    }




}

