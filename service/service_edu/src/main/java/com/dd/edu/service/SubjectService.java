package com.dd.edu.service;

import com.dd.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dd.edu.entity.subject.OneKindSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-20
 */
public interface SubjectService extends IService<Subject> {
    //添加课程分类
    void saveSubject(MultipartFile file,SubjectService subjectService);

    List<OneKindSubject> MakeListSubject();
}
