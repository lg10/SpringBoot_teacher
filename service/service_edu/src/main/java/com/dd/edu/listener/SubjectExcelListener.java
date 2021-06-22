package com.dd.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.config.CustomException.CustomException;
import com.dd.edu.entity.Subject;
import com.dd.edu.entity.excel.SubjectData;
import com.dd.edu.service.SubjectService;

import java.sql.Wrapper;
import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {



    public SubjectService subjectService;
    public SubjectExcelListener() { }
    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;    }


    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        System.out.println("-------"+subjectData);

        if (subjectData.equals(null)){
            throw new CustomException(20001,"文件数据为空");
        }
        //一行一行的添加，第一个值为一级分类，第二哥值为二级分类
        //如果添加的值与数据库中的一级分类不重复，则添加
        Subject oneSubject=this.oneSubjectService(subjectService,subjectData.getFirstLine());
        if (oneSubject.equals(null)){
            oneSubject=new Subject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getFirstLine());
            subjectService.save(oneSubject);
        }
        //获取一级分类的id值
        String pid=oneSubject.getId();

        //二级分类添加
        Subject twoSubject=this.twoSubjectService(subjectService,subjectData.getSecondLine(),pid);
//       if (twoSubject.equals(null)){
        if (twoSubject==null){
            twoSubject=new Subject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getSecondLine());
            subjectService.save(twoSubject);
        }




    }

    //判断一级分类不能重复添加
    private Subject oneSubjectService(SubjectService subjectService,String name){
        QueryWrapper<Subject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        Subject onesubject=subjectService.getOne(wrapper);
        return onesubject;
    }
    //判断二级分类不能重复添加
    private Subject twoSubjectService(SubjectService subjectService,String name,String pid){
        QueryWrapper<Subject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        Subject twosubject=subjectService.getOne(wrapper);
        return twosubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {


    }
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("33333333"+headMap);
    }
}
