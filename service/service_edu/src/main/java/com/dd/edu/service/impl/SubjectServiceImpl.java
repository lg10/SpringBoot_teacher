package com.dd.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.edu.easyExcel.DataDome;
import com.dd.edu.easyExcel.EasyExcelListener;
import com.dd.edu.entity.Subject;
import com.dd.edu.entity.excel.SubjectData;
import com.dd.edu.entity.subject.OneKindSubject;
import com.dd.edu.entity.subject.TwoKindSubject;
import com.dd.edu.listener.SubjectExcelListener;
import com.dd.edu.mapper.SubjectMapper;
import com.dd.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-20
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
//添加课程分类

    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            InputStream inputStream= file.getInputStream();
            EasyExcel.read(inputStream,SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneKindSubject> MakeListSubject() {

        //根据parent_id查询哪一些数据是属于---第一类别 并存放再oneSubject中
        QueryWrapper<Subject> oneWrapper= new QueryWrapper<>();
        oneWrapper.eq("parent_id","0");
        List<Subject> oneSubject=baseMapper.selectList(oneWrapper);


        //parent_id ！=0 的数据属于-----第二类别 存放 twoSubject中
        QueryWrapper<Subject>  twoWrapper=new QueryWrapper<>();
        twoWrapper.ne("parent_id","0");
        List<Subject> twoSubject =baseMapper.selectList(twoWrapper);

        //创建一个数组存放最终排好的数据
        List<OneKindSubject> finalSubject =new ArrayList<>();

        //用for循环遍历oneSubject
        for (int i = 0; i <oneSubject.size() ; i++) {
            //用get（i） 取到单个的数据对象
            Subject subject=oneSubject.get(i);
            //创建第一类别的对象，用于储存，title，id

            //方法一
            OneKindSubject oneKindSubject=new OneKindSubject();
//            oneKindSubject.setId(subject.getId());
//            oneKindSubject.setTitle(subject.getTitle());

            //方法二
            BeanUtils.copyProperties(subject,oneKindSubject);
            finalSubject.add(oneKindSubject);//将整个oneKindSubject，添加到finalSubject，用于抛出

            //创建一个数组 用于存储第二类别的数据
            List<TwoKindSubject> twoFinalSubject =new ArrayList<>();
            //遍历第二类数据
            for (int j = 0; j <twoSubject.size() ; j++) {
                //去除单个的数据
                Subject subject1=twoSubject.get(j);
                //如果单个数据中的parent_id等于第一类数据id值，（则为子类）
                if (subject1.getParentId().equals(subject.getId())){
                    //new一个 对象用于存放数据
                    TwoKindSubject twoKindSubject=new TwoKindSubject();
                    //方法一
                    twoKindSubject.setTitle(subject1.getTitle());
                    twoKindSubject.setId(subject1.getId());
                    twoFinalSubject.add(twoKindSubject);//将对象存放在，twoFinalSubject数组中
                    //方法二
//                    BeanUtils.copyProperties(subject1,twoKindSubject);
                }
            }
            //twoFinalSubject数组存放到oneKindSubject的Children的实例中
            oneKindSubject.setChildren(twoFinalSubject);
        }
        return finalSubject;
    }
}
