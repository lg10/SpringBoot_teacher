package com.dd.edu.controller;


import com.dd.edu.entity.Subject;
import com.dd.edu.entity.subject.OneKindSubject;
import com.dd.edu.service.SubjectService;
import com.dd.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-20
 */
@RestController
@RequestMapping("/service_edu/subject_edu")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }
    @GetMapping("getAllSubject")
    public R GetAllSubject(){
        List<OneKindSubject> listSubject=subjectService.MakeListSubject();

        System.out.println(listSubject);
       return R.ok().data("list",listSubject);
    }

}

