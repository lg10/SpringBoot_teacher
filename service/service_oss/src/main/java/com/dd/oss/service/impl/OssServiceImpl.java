package com.dd.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.dd.oss.service.OssService;
import com.dd.oss.utils.GetPropertiesInfo;
import com.dd.utils.R;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String updloadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = GetPropertiesInfo.ENDPOINT ;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = GetPropertiesInfo.KEYID;
        String accessKeySecret = GetPropertiesInfo.KEYSECRET;
        String bucketName = GetPropertiesInfo.BUCKETNAME;


        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        //    String objectName = "<yourObjectName>";
        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取文件上传流
            InputStream inputStream=file.getInputStream();
            //获取上传文件的原始名称
            String fileName = file.getOriginalFilename();
            //解决文件名重复，导致文件被覆盖
            //思想:在文件名前加一个随机数 uuid生成一个数据，转化为string类型 ，再替换数据中的“-”
            String uuid=UUID.randomUUID().toString().replaceAll("-","");
            fileName=uuid+fileName;
            //解决文件过多堆积再一个文件夹里的问题， 利用时间建包，归类
            //获取当前时间
            String dataPath=new DateTime().toString("yyyy/MM/dd");
            fileName=dataPath+"/"+fileName;


            //第一个参数：bucket名称
            //第二个参数：上传到oss的文件路径和文件名称
            //第三个参数：上传文件的流
            ossClient.putObject(bucketName, fileName,inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            //拼接返回路径
            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
             }catch (Exception e){
            e.printStackTrace();
            }
        return null;
    }

    @Override
    public String delFile(String FileName) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = GetPropertiesInfo.ENDPOINT ;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = GetPropertiesInfo.KEYID;
        String accessKeySecret = GetPropertiesInfo.KEYSECRET;
        String bucketName = GetPropertiesInfo.BUCKETNAME;
// 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        String objectName =FileName;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, objectName);
// 关闭OSSClient。
        ossClient.shutdown();

        return "ok";
    }


}
