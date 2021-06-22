package com.dd.edu.controller;


import com.dd.utils.R;
import org.springframework.web.bind.annotation.*;

@CrossOrigin//解决跨域访问
@RequestMapping("/service_edu/teacher_login")
@RestController//将代码交给springboot管理
public class EduLoginTeacher {
    @PostMapping("login")
    private R login(){
        return R.ok().data("token","admin");

    }
    @GetMapping("info")
    private R getInfo(){
        return R.ok().data("roles","[admin]").data("name","xiankang").data("avatar","https://xiankanglg10.oss-cn-beijing.aliyuncs.com/1b3a3ded2fe4ab5890a916f2e9c0df9d.jpg");

    }
}
