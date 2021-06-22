package com.dd.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.dd.config.CustomException.CustomException;
import com.dd.edu.entity.EduTeacher;
import com.dd.edu.entity.vo.Queryteacher;
import com.dd.edu.service.EduTeacherService;

import com.dd.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-18
 */
@CrossOrigin//解决跨域访问
@Api(value = "讲师管理")
@RestController
@RequestMapping("/service_edu/teacher_edu")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @ApiOperation(value = "讲师查询")
    @GetMapping("findAll")
    public  R findAllTeacher(){
//        try {
//            int a=10/0;
//        }catch (Exception e){
//            throw new CustomException(20001,"自定义异常。。。。");
//        }

        List<EduTeacher> list=eduTeacherService.list(null);
        return R.ok().data("items",list);
    }
    @ApiOperation(value = "逻辑删除")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){
        boolean flag=eduTeacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //分页
    @ApiOperation(value = "分页")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R PageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //调用方法实现分页
        //调用方法的时候，将数据封装在pageteacher对象里
        eduTeacherService.page(pageTeacher, null);

        long total=pageTeacher.getTotal();//总记录数
        List<EduTeacher> records=pageTeacher.getRecords();//数据list集合
        //方法一
//        return R.ok().data("data",total).data("rows",records);
        //方法二
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);


    }

    //条件查询带分页的方法 current:当前页 limit:记录数
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) Queryteacher queryteacher){
        Page<EduTeacher> pageTeacher=new Page<>(current,limit);
        //构建条件组合
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();

        //多条件组合查询
        //m动态sql
        String name= queryteacher.getName();
        Integer level=queryteacher.getLevel();
        Date begin=queryteacher.getGmtCreate();
        Date end =queryteacher.getGmtCreate();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询
        eduTeacherService.page(pageTeacher,wrapper);

        long total=pageTeacher.getTotal();//总记录
        List<EduTeacher> records=pageTeacher.getRecords();//数据list集合

        return R.ok().data("total",total).data("rows",records);

    }

    //添加
    @ApiOperation(value = "添加")
    @PostMapping("addTeacher")
    public R AddTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save=eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //根据id 进行查询
    @ApiOperation("修改")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    //讲师修改
    @PostMapping("updateteacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flat =eduTeacherService.updateById(eduTeacher);
        if (flat){
            return R.ok();
        }
        else{
            return R.error();
        }
    }




}

