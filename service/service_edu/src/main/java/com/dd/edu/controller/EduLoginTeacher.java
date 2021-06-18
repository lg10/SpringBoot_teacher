package com.dd.edu.controller;


import utils.R;
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
        return R.ok().data("roles","[admin]").data("name","xiankang").data("avatar","https://www.enterdesk.com/bizhi/40278.html");

    }
}
