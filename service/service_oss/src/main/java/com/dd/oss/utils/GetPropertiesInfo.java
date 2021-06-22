package com.dd.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//当项目自己启动，spring接口，spring加载之后，执行接口方法 下面的 afterPropertiesSet()
@Component
public class GetPropertiesInfo implements InitializingBean {
//    aliyun.oss.file.endpoint=oss-cn-beijing.aliyuncs.com
//    aliyun.oss.file.keyid=LTAI4GG1MnUW6bsnbadb2wfS
//    aliyun.oss.file.keysecret=LTAI5tBNavPBE2UnvB5YpM5o
//#bucket可以在控制台创建，也可以使用java代码创建
//    aliyun.oss.file.bucketname=xiankanglg10
   @Value( "${aliyun.oss.file.endpoint}") //获取到properties的值
    private String endpoint;
    @Value( "${aliyun.oss.file.keyid}")
    private String keyid;
    @Value( "${aliyun.oss.file.keysecret}")
    private  String keysecret;
    @Value( "${aliyun.oss.file.bucketname}")
    private  String bucketname;


    //创建公共静态对象，用于外部的方法调配用
   public static String ENDPOINT;
    public static String  KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT=endpoint;
        KEYID=keyid;
        KEYSECRET=keysecret;
        BUCKETNAME=bucketname;

    }
}
