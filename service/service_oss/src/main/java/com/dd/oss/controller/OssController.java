package com.dd.oss.controller;

import com.dd.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.dd.utils.R;

import java.io.File;

@RestController
@RequestMapping("/service_oss/fileOss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    //上传头像
    @PostMapping("/file")
    private R updateOssFile(MultipartFile file){
        //获取上传文件  MultipartFile
        //返回oss上传路径
        String url=ossService.updloadFileAvatar(file);
        return  R.ok().data("url",url);

    }
    @GetMapping("/delFile/{FileName}")
    public   R delFile(@PathVariable String FileName){
        //提取文件名
        String name[]={"https://xiankanglg10.oss-cn-beijing.aliyuncs.com/"};
        for (String s : name) {
            // 使用replaceAll正则替换,replace不支持正则
            FileName = FileName.replaceAll(s,"");
        }
        //调用阿里云的删除方法
        ossService.delFile(FileName);

        return R.ok();
    }


}
